package datalayer;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public final class dbConnect {

   public Connection con;
   public Statement stmt;
   public static dbConnect db;
   private dbConnect() {
      String DBURL = "jdbc:mysql://mydbinstance.c71ud1joo1ez.eu-central-1.rds.amazonaws.com:3306/mydb?useSSL=false";
      String driver = "com.mysql.jdbc.Driver";
      String usern = "SSP";
      String pass = "123456";
      try {
         Class.forName(driver).newInstance();
         this.con = DriverManager.getConnection(DBURL,usern,pass);
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println(ex);
      }
   }

   public static synchronized dbConnect getConnection(){
      if(db == null){
         db = new dbConnect();
      }
      return db;
   }

   public static void main(String[] args)throws SQLException {
      Connection l = dbConnect.getConnection().con;
      System.out.println(l.toString());
   }
}
