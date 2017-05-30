package applayer;

/**
 *  Made by Mikkel Olsen
 */

class RecInfo {
   // Visse informationer bevidst udladt
   private static int maxOrderSize = 5;
   private static String owner = "Pernille A. Jensen";
   private static String title = "Kage Faktura";
   private static String company = "Pernille's Lækkerier";
   private static String subject = "Kage Bestilling";
   private static String logoPath = "logo.png";
   private static String cvrNumber = "(Indsæt CVR nummer her)";
   private static String address = "XXXXXX nr X, XXXX Danmark";
   private static String email = "pernilleslaekkerier@gmail.com";
   private static String phone = "XXXXXXXX";
   private static String accountNumber = "XXXXXXXXXX";
   private static String regNumber = "XXXX";

   static int getMaxOrderSize() {
      return maxOrderSize;
   }

   static String getOwner() {
      return owner;
   }

   static String getTitle() {
      return title;
   }

   static String getCompany() {
      return company;
   }

   static String getSubject() {
      return subject;
   }

   static String getLogoPath() {
      return logoPath;
   }

   static String getCvrNumber() {
      return cvrNumber;
   }

   static String getAddress() {
      return address;
   }

   static String getEmail() {
      return email;
   }

   static String getPhone() {
      return phone;
   }

   static String getAccountNumber() {
      return accountNumber;
   }

   static String getRegNumber() {
      return regNumber;
   }
}