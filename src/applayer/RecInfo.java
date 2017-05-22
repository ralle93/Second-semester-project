package applayer;

class RecInfo {
   private static int receiptID = 1;
   private static int maxOrderSize = 5;
   private static String author = "Pernille Jensen";
   private static String title = "Kage Kvittering";
   private static String creator = "Pernilles Kager";
   private static String subject = "Kage Bestilling";
   private static String logoPath = "data/ReceiptData/Logo.png";

   static int getReceiptID() {
      return receiptID;
   }

   static void setReceiptID(int receiptID) {
      RecInfo.receiptID = receiptID;
   }

   static int getMaxOrderSize() {
      return maxOrderSize;
   }

   static String getAuthor() {
      return author;
   }

   static void setAuthor(String author) {
      RecInfo.author = author;
   }

   static String getTitle() {
      return title;
   }

   static void setTitle(String title) {
      RecInfo.title = title;
   }

   static String getCreator() {
      return creator;
   }

   static void setCreator(String creator) {
      RecInfo.creator = creator;
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
}