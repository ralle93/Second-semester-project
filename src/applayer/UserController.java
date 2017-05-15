package applayer;

// Gonna change this class to a servlet when we have the frontend for it
public class UserController {

   public void validateUserInfo(String email, String password, String name, String phoneNumber) {
      System.out.println(isValidEmail(email));
      System.out.println(isValidPass(password));
      System.out.println(isValidName(name));

      System.out.println(!phoneNumber.isEmpty());
   }

   // Method to determine if the user has entered a valid looking e-mail address
   private boolean isValidEmail(String email) {
      // Check that e-mail contains '@', '.' and don't have spaces
      if (email.contains("@") && email.contains(".") && !email.contains(" ")) {
         // Check that '@' isn't in the start or end of the e-mail
         if (email.charAt(0) != '@' && email.charAt(email.length() - 1) != '@') {
            // Check that '.' isn't in the start or end of the e-mail
            if (email.charAt(0) != '.' && email.charAt(email.length() - 1) != '.') {
               String str = email.replaceAll("@", "");

               // Check if there is more than one '@'
               if (str.length() == email.length() - 1) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   // Method to check if the user has entered a strong enough password
   private boolean isValidPass(String password) {
      if (password.length() < 6) {
         return false;
      }

      return true;
   }

   // Simple method to determine if the user has entered a valid looking name
   private boolean isValidName(String name) {
      // Check that the name is not so short that there can't be a first and last name
      if (name.length() >= 3) {
         // Check that the name contains a space and dosn't start or end with one
         if (name.contains(" ") && name.charAt(0) != ' ' && name.charAt(name.length() - 1) != ' ') {
            return true;
         }
      }

      return false;
   }
}
