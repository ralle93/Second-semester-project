package datalayer;

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

   //Method to create user in user table in database.
   public boolean createUser(User user) {
      try{
         String query = "INSERT INTO `mydb`.`users` (`email`, `password`, `is_admin`, `created`, `name`, `phone_nr`)";
         query += " VALUES (?, ?, ?, ?, ?, ?);";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, user.getEmail());
         stmt.setString(2, user.getPassword());
         stmt.setBoolean(3, false);
         stmt.setDate(4, Date.valueOf(LocalDate.now()));
         stmt.setString(5, user.getName());
         stmt.setString(6,user.getPhoneNumber());

         return db.insertQuery(stmt);
      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return false;
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

   //method to check user credentials
   public User logInCheck(String email, String password){
      try{
         String query ="SELECT * FROM users WHERE email = ? AND password = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, email);
         stmt.setString(2, password);
         rs = db.resultQuery(stmt);
         if(rs.next()) {
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(7));
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
         String query = "INSERT INTO `mydb`.`http_requests` (`user_id`, `request`) VALUES (?, ?);";
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
   public void insertActivationLink(String activation, User user){
      try {
         String query = "INSERT INTO `mydb`.`activation` (user_id, activation_string) VALUES (?, ?);";
         stmt = conn.prepareStatement(query);
         stmt.setInt(1, user.getId());
         stmt.setString(2, activation);
         db.insertQuery(stmt);

      }catch(SQLException ex){
         ex.printStackTrace();
      }
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

   //method to fetch a user from their current http session id.
   public User fetchUserFromSession(String httpSession){
      try {
         String query = "SELECT * FROM http_requests WHERE request = ?";
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

   //method to create order in db
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

   public void fetchOrder(){

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
         query += "WHERE cake_id = " + id + ";";

         stmt = conn.prepareStatement(query);
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
      Cake c = d.getCakeFromID(2);

      System.out.println(c);
   }

}
