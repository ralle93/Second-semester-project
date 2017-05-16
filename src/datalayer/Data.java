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
            User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            return user;
         }
         return null;
      } catch(SQLException ex){
         ex.printStackTrace();
      }

      return null;
   }

   public User fetchUser(int id){
      String query
   }
   //method to create order in db
   public boolean createOrder(){

      return false;
   }

   public boolean createCake(){

      return false;
   }


   public static void main(String[] args)throws SQLException {
      User user = new User("bla@dinmorSøren.dk1","blub", "søren", "61616161");
      Data d = new Data();

   }

}
