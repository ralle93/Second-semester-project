package datalayer;

import java.sql.*;

public class testDB {

   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://mydbinstance.c71ud1joo1ez.eu-central-1.rds.amazonaws.com:3306/mydb";

   // Database credendials
   static final String USER = "SSP";
   static final String PASS = "123456";

   public static void main(String[] args) {
      isValidUserLogin("test", "test");
   }

   private static boolean isValidUserLogin(String userName, String userPassword) {

      boolean isValidUser = false;

      Connection conn = null;
      Statement stmt = null;
      String sql = "";

      try {
         // Register JDBC driver
         Class.forName(JDBC_DRIVER);

         // Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute a query
         System.out.println("Creating statement...");
         stmt = conn.createStatement();

         sql = "SELECT * FROM users WHERE user_name = \"" + userName + "\" AND user_password = \"" + userPassword + "\"";
         System.out.println(sql);

         ResultSet rs = stmt.executeQuery(sql);

         // Extract data from resultset
         if (rs.next()) {
            isValidUser = true;
         }

         // Clean up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) {
         // JDBC errors
         se.printStackTrace();
      } catch (Exception e) {
         // Class.forName errors
         e.printStackTrace();
      } finally {
         // Finally block used to close resources
         try {
            if (stmt != null) {
               stmt.close();
            }
         } catch (SQLException se2) {
         } // Nothing we can do
         try {
            if (conn != null) {
               conn.close();
            }
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }

      System.out.println("Closing database connection...");

      return isValidUser;
   }
}