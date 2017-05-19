package applayer;

public class RecInfo {
   private static int receiptID = 1;
   private static int maxOrderSize = 5;
   private static String author = "Pernille Jensen";
   private static String title = "Kage Kvittering";
   private static String creator = "Pernilles Kager";
   private static String subject = "Kage Bestilling";
   private static String logoPath = "data/ReceiptData/Logo.png";

   public static int getReceiptID() {
      return receiptID;
   }

   public static void setReceiptID(int receiptID) {
      RecInfo.receiptID = receiptID;
   }

   public static int getMaxOrderSize() {
      return maxOrderSize;
   }

   public static String getAuthor() {
      return author;
   }

   public static void setAuthor(String author) {
      RecInfo.author = author;
   }

   public static String getTitle() {
      return title;
   }

   public static void setTitle(String title) {
      RecInfo.title = title;
   }

   public static String getCreator() {
      return creator;
   }

   public static void setCreator(String creator) {
      RecInfo.creator = creator;
   }

   public static String getSubject() {
      return subject;
   }

   public static void setSubject(String subject) {
      RecInfo.subject = subject;
   }

   public static String getLogoPath() {
      return logoPath;
   }

   public static void setLogoPath(String logoPath) {
      RecInfo.logoPath = logoPath;
   }
}