package datalayer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.Calendar;

public class ReceiptCreator {
   private static int receiptID = 1;

   private static String author = "Pernille Jensen";
   private static String title = "Kage Kvittering";
   private static String creator = "Pernilles Kager";
   private static String subject = "Kage Bestilling";

   /** DIMENSIONS OF PDF DOCUMENTS
    * IN PIXELS: 792 height / 612 width
    **/

   // TIL tests af pdfBox funktioner
   public static void main(String[] args) throws IOException {
      PDDocument document = new PDDocument();

      PDPage blankpage = new PDPage();
      document.addPage(blankpage);

      PDImageXObject pdImage = PDImageXObject.createFromFile("ReceiptData/Logo.png", document);
      PDPageContentStream contents = new PDPageContentStream(document, blankpage);
      contents.drawImage(pdImage,0,692,240,100);
      contents.close();

      document.setDocumentInformation(addInformation(document));
      document.save("pdf_examples/KageKvittering" + appendZeros(receiptID) + ".pdf");
      System.out.println("PDF created!");

      document.close();
   }

   private static String appendZeros(int number) {
      return String.format("%06d", number);
   }

   private static PDDocumentInformation addInformation(PDDocument document) {
      PDDocumentInformation pdd = document.getDocumentInformation();

      pdd.setAuthor(author);
      pdd.setTitle(title);
      pdd.setCreator(creator);
      pdd.setSubject(subject);
      Calendar date = Calendar.getInstance();
      pdd.setCreationDate(date);

      return pdd;
   }
}