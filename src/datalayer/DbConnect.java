package datalayer;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public final class DbConnect {

   public Connection conn;
   public ResultSet rs;
   public Statement stmt;
   public static DbConnect db;

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

   public ResultSet resultQuery(String query) throws SQLException{
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      return rs;
   }

   public boolean insertQuery(PreparedStatement stmt) throws SQLException{
      if(stmt.executeUpdate() > 0) return true;
      return false;
   }
}
