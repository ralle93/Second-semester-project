package datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;

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
   //method to check user credentials
   public User logInCheck(String email, String password){
      try{
         String query ="SELECT * FROM users WHERE email = ? AND password = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, email);
         stmt.setString(2, password);
         rs = db.resultQuery(stmt);
         if(rs.next()) {
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
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
         String query = "INSERT INTO `mydb`.`http_requests` (`user_id`, `requests`) VALUES (?, ?);";
         stmt = conn.prepareStatement(query);

         stmt.setInt(1,user.getId());
         stmt.setString(2,session);

         return db.insertQuery(stmt);
      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return false;

   }

   public User getUserFromId(int id){
      try {
         String query = "SELECT * FROM users WHERE user_id = ?";
         stmt = conn.prepareStatement(query);
         stmt.setInt(1,id);
         rs = db.resultQuery(stmt);
         if(rs.next()){
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            return user;
         }

      }catch(SQLException ex){
         ex.printStackTrace();
      }
      return null;
   }

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
   public boolean createOrder(){

      return false;
   }

   public boolean createCake(){

      return false;
   }

   public static void main(String[] args)throws SQLException {
      User user = new User(5,"bla@dinmorSøren.dk1","blub", "søren", "61616161");
      Data d = new Data();


   }

}
