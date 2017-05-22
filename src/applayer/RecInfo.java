package applayer;

class RecInfo {
   private static int receiptID = 1;
   private static int maxOrderSize = 5;
   private static String owner = "Pernille A. Jensen";
   private static String title = "Kage Kvittering";
   private static String company = "Pernille's Lækkerier";
   private static String subject = "Kage Bestilling";
   private static String logoPath = "data/ReceiptData/Logo.png";
   private static String email = "pernilleslaekkerier@gmail.com";
   private static String phone = "+45 22379163";

   static int getReceiptID() {
      return receiptID;
   }

   static void setReceiptID(int receiptID) {
      RecInfo.receiptID = receiptID;
   }

   static int getMaxOrderSize() {
      return maxOrderSize;
   }

   static String getOwner() {
      return owner;
   }

   static void setOwner(String owner) {
      RecInfo.owner = owner;
   }

   static String getTitle() {
      return title;
   }

   static void setTitle(String title) {
      RecInfo.title = title;
   }

   static String getCompany() {
      return company;
   }

   static void setCompany(String company) {
      RecInfo.company = company;
   }

   static String getSubject() {
      return subject;
   }

   static void setSubject(String subject) {
      RecInfo.subject = subject;
   }

   static String getLogoPath() {
      return logoPath;
   }

   static void setLogoPath(String logoPath) {
      RecInfo.logoPath = logoPath;
   }

   public static String getEmail() {
      return email;
   }

   public static void setEmail(String email) {
      RecInfo.email = email;
   }

   public static String getPhone() {
      return phone;
   }

   public static void setPhone(String phone) {
      RecInfo.phone = phone;
   }
}