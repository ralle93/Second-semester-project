package datalayer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.Calendar;

class ReceiptCreator {
   private PDDocument doc;
   private PDPage page;
   private OrderList orderList;
   private User user;
   private PDPageContentStream contentStream;

   /** DIMENSIONS OF PDF DOCUMENTS
    * IN PIXELS: 792 height / 612 width
    **/
   private int beginOrderListX = 30;
   private int beginOrderListY = 400;
   private int amountColumn = 300;
   private int priceColumn = 400;
   private int totalColumn = 500;

   ReceiptCreator(OrderList orderList, User user) {
      doc = new PDDocument();
      page = new PDPage();
      doc.addPage(page);

      this.orderList = orderList;
      this.user = user;
   }

   // TODO KAN ÆNDRES TIL AT RETURNERE PDF DOKUMENTET
   void newReceipt() {
      try {
         contentStream = new PDPageContentStream(doc, page);
         formatData();
         formatOrder();
         contentStream.close();
         saveDocument();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void addLogo() {
      try {
         PDImageXObject pdImage = PDImageXObject.createFromFile(RecInfo.getLogoPath(), doc);
         contentStream.drawImage(pdImage, 30, 662, 240, 100);
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("ERROR: LOGO MISSING");
      }
   }

   private void orderOverview() {
      int offset = 50;

      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginOrderListX,beginOrderListY + offset);
         String name = "Kage / Beskrivelse:";
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginOrderListX + amountColumn,beginOrderListY + offset);
         String amount = "Personer / Antal:";
         contentStream.showText(amount);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginOrderListX + priceColumn,beginOrderListY + offset);
         String price = "Enkelt pris:";
         contentStream.showText(price);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginOrderListX + totalColumn,beginOrderListY + offset);
         String total = "Total:";
         contentStream.showText(total);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void formatData() {
      addLogo();
      orderOverview();
   }

   private void orderName() {
      try {
         for (int i = 0; i < RecInfo.getMaxOrderSize(); i++) {
            if (orderList.getOrder(i) != null) {
               contentStream.beginText();
               contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
               contentStream.newLineAtOffset(beginOrderListX, beginOrderListY - (i * 30));
               String text = orderList.getOrder(i).getCake().getName();
               contentStream.showText(text);
               contentStream.endText();
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void orderDesc() {
      try {
         for (int i = 0; i < RecInfo.getMaxOrderSize(); i++) {
            if (orderList.getOrder(i) != null) {
               contentStream.beginText();
               contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
               contentStream.newLineAtOffset(beginOrderListX, (beginOrderListY - 10) - (i * 30));
               String text = orderList.getOrder(i).getCake().getDescription();
               contentStream.showText(text);
               contentStream.endText();
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void orderAmount() {
      try {
         for (int i = 0; i < RecInfo.getMaxOrderSize(); i++) {
            if (orderList.getOrder(i) != null) {
               contentStream.beginText();
               contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
               contentStream.newLineAtOffset(beginOrderListX + amountColumn, beginOrderListY - (i * 30));
               String text = orderList.getOrder(i).getAmount() + "";
               contentStream.showText(text);
               contentStream.endText();
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void orderPrice() {
      try {
         for (int i = 0; i < RecInfo.getMaxOrderSize(); i++) {
            if (orderList.getOrder(i) != null) {
               contentStream.beginText();
               contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
               contentStream.newLineAtOffset(beginOrderListX + priceColumn, beginOrderListY - (i * 30));
               String text = orderList.getOrder(i).getCake().getPrice() + "";
               contentStream.showText(text);
               contentStream.endText();
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void orderTotal() {
      try {
         for (int i = 0; i < RecInfo.getMaxOrderSize(); i++) {
            if (orderList.getOrder(i) != null) {
               contentStream.beginText();
               contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
               contentStream.newLineAtOffset(beginOrderListX + totalColumn, beginOrderListY - (i * 30));
               String text = orderList.getOrder(i).getPrice() + "";
               contentStream.showText(text);
               contentStream.endText();
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void calcTotal() {

   }

   private void formatOrder() {
      orderName();
      orderDesc();
      orderAmount();
      orderPrice();
      orderTotal();
      calcTotal();
   }

   private PDDocumentInformation addInformation(PDDocument document) {
      PDDocumentInformation pdd = document.getDocumentInformation();

      pdd.setAuthor(RecInfo.getAuthor());
      pdd.setTitle(RecInfo.getTitle());
      pdd.setCreator(RecInfo.getCreator());
      pdd.setSubject(RecInfo.getSubject());
      Calendar date = Calendar.getInstance();
      pdd.setCreationDate(date);

      return pdd;
   }

   private String getID() {
      int number = RecInfo.getReceiptID();
      return String.format("%06d", number);
   }

   private void saveDocument() {
      try {
         doc.setDocumentInformation(addInformation(doc));
         doc.save("data/pdf_examples/KageKvittering" + getID() + ".pdf");
         doc.close();
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("ERROR: FILE ERROR");
      }
   }
}