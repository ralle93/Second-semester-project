package datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Data {
   Connection conn = DbConnect.getConnection().conn;
   PreparedStatement stmt;
   ResultSet rs;

   public boolean createUser(User user){
      String query = "INSERT INTO `mydb`.`users` (`email`, `password`, `is_admin`, `created`, `name`)";
      query += " VALUES (?, ?, ?, ?, ?);";
     // stmt = conn.createStatement(query);



      return false;
   }


}
