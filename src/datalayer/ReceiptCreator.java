package datalayer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.Calendar;

class ReceiptCreator {
   private static PDDocument doc;
   private static PDPage page;
   /** DIMENSIONS OF PDF DOCUMENTS
    * IN PIXELS: 792 height / 612 width
    **/

   static void newReceipt() {
      doc = new PDDocument();
      page = new PDPage();
      doc.addPage(page);

      formatDocument();
      encryptDocument();
      saveDocument();

      doc = null;
      page = null;
   }

   private static void addLogo() {
      try {
         PDImageXObject pdImage = PDImageXObject.createFromFile("ReceiptData/Logo.png", doc);
         PDPageContentStream contents = new PDPageContentStream(doc, page);
         contents.drawImage(pdImage, 30, 662, 240, 100);
         contents.close();
      } catch (IOException e) {
         System.out.println("ERROR: LOGO MISSING");
      }
   }

   private static void formatDocument() {
      addLogo();
   }

   private static void encryptDocument() {
      AccessPermission ap = new AccessPermission();
      ap.canExtractContent();

      StandardProtectionPolicy spp = new StandardProtectionPolicy("", "", ap);
      spp.setEncryptionKeyLength(128);
      spp.setPermissions(ap);
   }

   private static PDDocumentInformation addInformation(PDDocument document) {
      PDDocumentInformation pdd = document.getDocumentInformation();

      pdd.setAuthor(RecInfo.getAuthor());
      pdd.setTitle(RecInfo.getTitle());
      pdd.setCreator(RecInfo.getCreator());
      pdd.setSubject(RecInfo.getSubject());
      Calendar date = Calendar.getInstance();
      pdd.setCreationDate(date);

      return pdd;
   }

   private static String getID() {
      int number = RecInfo.getReceiptID();
      return String.format("%06d", number);
   }

   private static void saveDocument() {
      try {
         doc.setDocumentInformation(addInformation(doc));
         doc.save("pdf_examples/KageKvittering" + getID() + ".pdf");
         doc.close();
      } catch (IOException e) {
         System.out.println("ERROR: FILE ERROR");
         e.printStackTrace();
      }
   }
}