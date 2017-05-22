package applayer;

class RecInfo {
   private static int receiptID = 1;//TODO
   private static int maxOrderSize = 5;
   private static String owner = "Pernille A. Jensen";
   private static String title = "Kage Kvittering";
   private static String company = "Pernille's Lækkerier";
   private static String subject = "Kage Bestilling";
   private static String logoPath = "data/ReceiptData/Logo.png";
   private static String cvrNumber = "(Indsæt CVR nummer her)"; //TODO
   private static String address = "Lindebo 17 st. 0098, 2680 Taastrup";
   private static String email = "pernilleslaekkerier@gmail.com";
   private static String phone = "22379163";
   private static String accountNumber = "XXXXXXXXXX"; //TODO
   private static String regNumber = "XXXX";//TODO

   static int getReceiptID() {
      return receiptID;
   }

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