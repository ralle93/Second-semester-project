package applayer;

public class UserController {

   public void validateUserInfo(String email) {//, String password, String name, String phoneNumber) {
      // Check e-mail
      System.out.println(isValidEmail(email));
   }

   // Method determine if the user has entered a valid looking e-mail address
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
}
