package datalayer;

import Security.Hash;
import applayer.Cake;
import applayer.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Data {

   private DbConnect db = DbConnect.getConnection();
   private Connection conn = db.conn;
   private PreparedStatement stmt;
   private ResultSet rs;

   //Method to create user in user table in database, returns assigned user id.
   public int createUser(User user) {
      try{
         // Add user to users table
         String query = "INSERT INTO `mydb`.`users` (`email`, `password`, `is_admin`, `created`, `name`, `phone_nr`, `activated`) ";
         query += "VALUES (?, ?, ?, ?, ?, ?, ?);";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, user.getEmail());
         stmt.setString(2, Hash.hashPW(user.getPassword()));
         stmt.setBoolean(3, false);
         stmt.setDate(4, Date.valueOf(LocalDate.now()));
         stmt.setString(5, user.getName());
         stmt.setString(6,user.getPhoneNumber());
         stmt.setBoolean(7, false);

         db.insertQuery(stmt);

         // Get user id from newly created user
         query = "SELECT last_insert_id() FROM mydb.users;";
         stmt = conn.prepareStatement(query);

         rs = db.resultQuery(stmt);
         if(rs.next()) {
            return rs.getInt(1);
         }
         return -1;
      }catch(SQLException ex){
         ex.printStackTrace();
      }

      return -1;
   }

   // Method to edit a user
   public boolean editUser(User user) {
      try{
         String query = "UPDATE mydb.users SET email = ?, password = ?, name = ?, phone_nr = ? ";
         query += "WHERE user_id = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, user.getEmail());
         stmt.setString(2, user.getPassword());
         stmt.setString(3, user.getName());
         stmt.setString(4, user.getPhoneNumber());
         stmt.setInt(5, user.getId());

         return db.insertQuery(stmt);
      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return false;
   }

   // Change password from user id
   public boolean changeUserPass(int id, String password) {
      try{
         String query = "UPDATE mydb.users SET password = ? ";
         query += "WHERE user_id = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, password);
         stmt.setInt(2, id);

         return db.insertQuery(stmt);
      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return false;
   }

   //method to check user credentials
   public User logInCheck(String email, String password){
      try{
         String query ="SELECT * FROM users WHERE email = ? AND password = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, email);
         stmt.setString(2, Hash.hashPW(password));
         rs = db.resultQuery(stmt);
         if(rs.next()) {
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                  rs.getString(6), rs.getString(7), rs.getBoolean(8));
            return user;
         }
         return null;
      } catch(SQLException ex){
         ex.printStackTrace();
      }
      return null;
   }

   public boolean httpSessionAdd(User user, String session){
      try {
         String query = "INSERT INTO `mydb`.`http_session` (`user_id`, `session`) VALUES (?, ?);";
         stmt = conn.prepareStatement(query);

         stmt.setInt(1,user.getId());
         stmt.setString(2,session);

         return db.insertQuery(stmt);
      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return false;

   }

   // adds activation string and connects a user_id in the database
   // replaces if one with the same user_id allready exists
   public void insertActivationLink(String activation, int userID){
      try {
         String query = "INSERT INTO `mydb`.`activate_reset_keys` (user_id, activation_string) VALUES (?, ?) ";
         query += "ON DUPLICATE KEY UPDATE key_string = VALUES(key_string);";
         stmt = conn.prepareStatement(query);

         stmt.setInt(1, userID);
         stmt.setString(2, activation);
         db.insertQuery(stmt);

      }catch(SQLException ex){
         ex.printStackTrace();
      }
   }

   public void removeActivationLink(int userID) {
      try {
         String query = "DELETE FROM `mydb`.`activate_reset_keys` WHERE user_id = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setInt(1, userID);
         db.insertQuery(stmt);

      }catch(SQLException ex){
         ex.printStackTrace();
      }
   }

   // Get user id from activation/reset pass key
   public int getUserIdFromKey(String key) {
      try {
         String query = "SELECT user_id FROM activate_reset_keys WHERE key_string = ?";
         stmt = conn.prepareStatement(query);
         stmt.setString(1, key);

         rs = db.resultQuery(stmt);
         if(rs.next()){
            return rs.getInt(1);
         }

      }catch(SQLException ex){
         ex.printStackTrace();
      }

      return -1;
   }

   public boolean activateUser(int userID) {
      try{
         String query = "UPDATE mydb.users SET activated = ? ";
         query += "WHERE user_id = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setBoolean(1,true);
         stmt.setInt(2, userID);

         return db.insertQuery(stmt);
      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return false;
   }

   // fetch a user solely based on their ID(primary key in MySQL db)
   public User getUserFromId(int id){
      try {
         String query = "SELECT * FROM users WHERE user_id = ?";
         stmt = conn.prepareStatement(query);
         stmt.setInt(1,id);
         rs = db.resultQuery(stmt);
         if(rs.next()){
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(7));
            return user;
         }
      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return null;
   }

   // Method to get userID from email address
   public User getUserFromEmail(String email){
      try{
         String query ="SELECT * FROM users WHERE email = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, email);
         rs = db.resultQuery(stmt);
         if(rs.next()) {
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                  rs.getString(6), rs.getString(7), rs.getBoolean(8));
            return user;
         }
         return null;
      } catch(SQLException ex){
         ex.printStackTrace();
      }
      return null;
   }

   //method to fetch a user from their current http session id.
   public User fetchUserFromSession(String httpSession){
      try {
         String query = "SELECT * FROM http_session WHERE session_string = ?";
         stmt = conn.prepareStatement(query);
         stmt.setString(1, httpSession);
         rs = db.resultQuery(stmt);
         if(rs.next()){
           return getUserFromId(rs.getInt(2));
         }
      } catch(SQLException ex){
         ex.printStackTrace();
      }
      return null;
   }

   //method to create order in db NOT DONE
   public boolean createOrder(User user){
      try{
         String query = "INSERT INTO order (`user_id` , `created`) VALUES (?, ?);";
         stmt = conn.prepareStatement(query);
         stmt.setInt(1, user.getId());
         stmt.setDate(2,Date.valueOf(LocalDate.now()));
         db.insertQuery(stmt);

      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return false;
   }

   public void createOrder(){

   }
   public void fetchAllOrders(){

   }

   public ArrayList<Cake> getCakes() {
      try{
         String query ="SELECT * FROM mydb.cake;";
         stmt = conn.prepareStatement(query);

         rs = db.resultQuery(stmt);
         ArrayList<Cake> cakes = new ArrayList<>();

         while(rs.next()) {
            Cake cake = new Cake(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
            cakes.add(cake);
         }

         return cakes;
      } catch(SQLException ex){
         ex.printStackTrace();
      }
      return null;
   }

   public Cake getCakeFromID(int id) {
      try{
         String query ="SELECT * FROM mydb.cake ";
         query += "WHERE cake_id = ?;";

         stmt = conn.prepareStatement(query);
         stmt.setInt(1, id);
         rs = db.resultQuery(stmt);

         if (rs.next()) {
            return new Cake(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
         }

      } catch(SQLException ex){
         ex.printStackTrace();
      }

      return null;
   }

   public static void main(String[] args)throws SQLException {
      Data d = new Data();

      d.changeUserPass(31, "test");
   }

}
