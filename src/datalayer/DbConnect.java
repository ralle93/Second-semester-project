package datalayer;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//Singleton class in charge of getting connection to db and fetch/pulling data
public final class DbConnect {

   private Connection conn;
   private ResultSet rs;
   static DbConnect db;

   private DbConnect() {
      String DBURL = "jdbc:mysql://mydbinstance.c71ud1joo1ez.eu-central-1.rds.amazonaws.com:3306/mydb?useSSL=false";
      String driver = "com.mysql.jdbc.Driver";
      String usern = "SSP";
      String pass = "123456";
      try {
         Class.forName(driver).newInstance();
         this.conn = DriverManager.getConnection(DBURL,usern,pass);
      } catch (Exception ex) {
         ex.printStackTrace();
         System.out.println(ex);
      }
   }

   public static DbConnect getConnection(){
      if(db == null){
         db = new DbConnect();
      }
      return db;
   }

   public ResultSet resultQuery(PreparedStatement stmt) throws SQLException{
      rs = stmt.executeQuery();
      return rs;
   }

   public boolean insertQuery(PreparedStatement stmt) throws SQLException{
      if(stmt.executeUpdate() > 0) return true;
      return false;
   }
}
