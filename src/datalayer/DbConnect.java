package datalayer;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

// Singleton class in charge of getting connection to db and fetching/uploading data
// Made by Rasmus Dreier Thrane
final class DbConnect {
   Connection conn;
   private static DbConnect db;

   private DbConnect() {
      String DBURL = "jdbc:mysql://mydbinstance.c71ud1joo1ez.eu-central-1.rds.amazonaws.com:3306/mydb?useSSL=false";
      String driver = "com.mysql.jdbc.Driver";
      String usern = "SSP";
      String pass = "#&7@+D*}7!qbJ_d";
      try {
         Class.forName(driver).newInstance();
         this.conn = DriverManager.getConnection(DBURL,usern,pass);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   static DbConnect getConnection(){
      if(db == null){
         db = new DbConnect();
      }
      return db;
   }

   ResultSet resultQuery(PreparedStatement stmt) throws SQLException{
      return stmt.executeQuery();
   }

   boolean insertQuery(PreparedStatement stmt) throws SQLException{
      return stmt.executeUpdate() > 0;
   }
}