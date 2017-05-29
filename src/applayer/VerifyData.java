package applayer;

import java.time.LocalDate;

// Class to verifying different user input, like address and phone number
public class VerifyData {

   // Method to determine if the user has entered a valid looking e-mail address
   public static boolean isValidEmail(String email) {
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
   public static boolean isValidPass(String password) {
      return password.length() >= 6;
   }

   // Simple method to determine if the user has entered a valid looking name
   public static boolean isValidName(String name) {
      // Check that the name is not so short that there can't be a first and last name
      if (name.length() >= 3) {
         // Check that the name contains a space and dosn't start or end with one
         if (name.contains(" ") && name.charAt(0) != ' ' && name.charAt(name.length() - 1) != ' ') {
            return true;
         }
      }

      return false;
   }

   // Method to check if the user entered a valid phone number
   public static boolean isValidNumber(String phoneNumber) {
      return !(phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.equals(""));
   }

   // Method to check for valid address
   public static boolean isValidAddress(String address) {
      return !address.isEmpty();
   }

   private static final int TIME_FOR_DELIVERY = 7;

   public static boolean isValidDeliveryDate(LocalDate date) {
      LocalDate possibleDeliveryDate = LocalDate.now().plusDays(TIME_FOR_DELIVERY - 1);

      return date.isAfter(possibleDeliveryDate);
   }
}