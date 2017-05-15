package applayer;

public class User {
   private String email;
   private String password;
   private String name;
   private boolean isAdmin;

   public User(String email, String password, String name) {
      this.email = email;
      this.password = password;
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public String getPassword() {
      return password;
   }

   public String getName() {
      return name;
   }

   public boolean getIsAdmin() {
      return isAdmin;
   }
}