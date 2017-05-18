package datalayer;

public class User {
   private int id;
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

   public User(int id, String email, String password, String name, String phoneNumber) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.admin = false;
   }

   public String toString() {
      return "[" + id + ", " + email + ", " + password + ", " + name + ", " + phoneNumber + ", " + admin + "]";
   }

   public String getEmail() {
      return email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getName() {
      return name;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public int getId() {
      return id;
   }

   public boolean isAdmin() {
      return admin;
   }
}