package datalayer;

public class User {
   private String email;
   private String password;
   private String name;
   private String phoneNumber;
   private boolean admin;

   public User(String email, String password, String name, String phoneNumber) {
      this.email = email;
      this.password = password;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.admin = false;
   }

   public String toString() {
      return "[" + email + ", " + password + ", " + name + ", " + phoneNumber + "]";
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

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public boolean isAdmin() {
      return admin;
   }
}