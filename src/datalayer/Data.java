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
   public boolean logInCheck(User user){
      try{
         String query ="SELECT email, password FROM users WHERE email = ? AND password = ?;";
         stmt = conn.prepareStatement(query);

         stmt.setString(1, user.getEmail());
         stmt.setString(2, user.getPassword());
         rs = db.resultQuery(stmt, query);
         if(rs.next()) return true;
         return false;
      } catch(SQLException ex){
         ex.printStackTrace();
      }

      return false;
   }

   public boolean createOrder(){

      return false;
   }


   public static void main(String[] args)throws SQLException {
      User user = new User("bla@dinmorSøren.dk1","blub", "søren", "61616161");
      Data d = new Data();
      System.out.println(d.logInCheck(user));
   }

}
