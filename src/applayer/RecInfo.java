package applayer;

class RecInfo {
   private static int receiptID = 1;
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
   private static String accountNumber = "XXXX-XXXX-XXXX";
   private static String regNumber = "XXXX";

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

   public static String getCvrNumber() {
      return cvrNumber;
   }

   public static String getAddress() {
      return address;
   }

   public static void setAddress(String address) {
      RecInfo.address = address;
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

   public static String getAccountNumber() {
      return accountNumber;
   }

   public static void setAccountNumber(String accountNumber) {
      RecInfo.accountNumber = accountNumber;
   }

   public static String getRegNumber() {
      return regNumber;
   }

   public static void setRegNumber(String regNumber) {
      RecInfo.regNumber = regNumber;
   }
}