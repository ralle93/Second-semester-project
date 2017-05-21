package applayer;

public class User {
   private int id;
   private String email;
   private String password;
   private String name;
   private String phoneNumber;
   private boolean admin;
   private boolean activated;

   public User(String email, String password, String name, String phoneNumber) {
      this.email = email;
      this.password = password;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.admin = false;
      this.activated = true;
   }

   public User(int id, String email, String password, String name, String phoneNumber) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.admin = false;
      this.activated = true;
   }

   public User(int id, String email, String password, String name, String phoneNumber, boolean activated) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.admin = false;
      this.activated = activated;
   }

   public String toString() {
      return "[" + id + ", " + email + ", " + password + ", " + name + ", " + phoneNumber + ", " + admin + ", " + activated + "]";
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

   public boolean isActivated() {
      return activated;
   }
}