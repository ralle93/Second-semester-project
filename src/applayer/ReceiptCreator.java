package applayer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

class ReceiptCreator {
   private PDDocument doc;
   private PDPage page;
   private Order order;
   private User user;
   private PDPageContentStream contentStream;

   /** DIMENSIONS OF PDF DOCUMENTS
    * IN PIXELS: 792 height / 612 width
    **/
   private int beginInfoX = 30;
   private int beginInfoY = 630;
   private int infoLineSpace = 20;

   private int beginOrderListY = 500;
   private int orderLineSpace = 40;
   private int amountColumn = 300;
   private int priceColumn = 400;
   private int totalColumn = 500;

   private String priceEnding = ",- kr.";

   ReceiptCreator(Order order, User user) {
      doc = new PDDocument();
      page = new PDPage();
      doc.addPage(page);

      this.order = order;
      this.user = user;

      newReceipt();
   }

   // TODO KAN ÆNDRES TIL AT RETURNERE PDF DOKUMENTET
   private void newReceipt() {
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

   private void addUserInfo() {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
         contentStream.newLineAtOffset(beginInfoX, beginInfoY);
         String title = "Køber Information:";
         contentStream.showText(title);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX,beginInfoY - infoLineSpace);
         String name = "Navn: " + user.getName();
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX,beginInfoY - 2 * infoLineSpace);
         String email = "E-mail: " + user.getEmail();
         contentStream.showText(email);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX,beginInfoY - 3 * infoLineSpace);
         String phone = "Telefon: " + user.getPhoneNumber();
         contentStream.showText(phone);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void addSellerInfo() {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY + 2 * infoLineSpace);
         String title = "Firma Information:";
         contentStream.showText(title);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY + infoLineSpace);
         String company = "Firma: " + RecInfo.getCompany();
         contentStream.showText(company);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn, beginInfoY);
         String name = "Navn: " + RecInfo.getOwner();
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY - infoLineSpace);
         String email = "E-mail: " + RecInfo.getEmail();
         contentStream.showText(email);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY - 2 * infoLineSpace);
         String phone = "Telefon: " + RecInfo.getPhone();
         contentStream.showText(phone);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY - 3 * infoLineSpace);
         String date = "Afsendt Dato: " + LocalDate.now();
         contentStream.showText(date);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void drawLine() {
      try {
         contentStream.addRect(30,551,552,1);
         contentStream.fill();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void orderOverview() {
      int offset = 30;

      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX,beginOrderListY + offset);
         String name = "Kage / Beskrivelse:";
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginOrderListY + offset);
         String amount = "Personer / Antal:";
         contentStream.showText(amount);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + priceColumn,beginOrderListY + offset);
         String price = "Enkelt pris:";
         contentStream.showText(price);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + totalColumn,beginOrderListY + offset);
         String total = "Total:";
         contentStream.showText(total);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void formatData() {
      addLogo();
      addUserInfo();
      addSellerInfo();
      drawLine();
      orderOverview();
   }

   private void itemName(int i) {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX, beginOrderListY - (i * orderLineSpace));
         String text = order.getLineItem(i).getCake().getName();
         contentStream.showText(text);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void itemDesc(int i) {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ITALIC, 10);
         contentStream.newLineAtOffset(beginInfoX, (beginOrderListY - 10) - (i * orderLineSpace));
         String text = order.getLineItem(i).getCake().getDescription();
         contentStream.showText(text);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void itemAmount(int i) {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn, beginOrderListY - (i * orderLineSpace));
         String text = order.getLineItem(i).getAmount() + "";
         contentStream.showText(text);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void itemPrice(int i) {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + priceColumn, beginOrderListY - (i * orderLineSpace));
         String text = order.getLineItem(i).getCake().getPrice() + priceEnding;
         contentStream.showText(text);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void itemTotal(int i) {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + totalColumn, beginOrderListY - (i * orderLineSpace));
         String text = order.getLineItem(i).getPrice() + priceEnding;
         contentStream.showText(text);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void calcTotal() {
      int totalLine = RecInfo.getMaxOrderSize() * orderLineSpace;
      int vatLine = totalLine + 20;

      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
         contentStream.newLineAtOffset(beginInfoX, beginOrderListY - totalLine);
         String totalText = "Total:";
         contentStream.showText(totalText);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
         contentStream.newLineAtOffset(beginInfoX + totalColumn, beginOrderListY - totalLine);
         String total = order.getTotal() + priceEnding;
         contentStream.showText(total);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
         contentStream.newLineAtOffset(beginInfoX, beginOrderListY - vatLine);
         String vatText = "Heraf Moms 25% :";
         contentStream.showText(vatText);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
         contentStream.newLineAtOffset(beginInfoX + totalColumn, beginOrderListY - vatLine);
         double calcVAT = Math.round(((double) order.getTotal() * 0.25) * 100.0) / 100.0;
         String vat = calcVAT + " kr.";
         contentStream.showText(vat);
         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void formatOrder() {
      for (int i = 0; i < RecInfo.getMaxOrderSize(); i++) {
         if (order.getLineItem(i) != null) {
            itemName(i);
            itemDesc(i);
            itemAmount(i);
            itemPrice(i);
            itemTotal(i);
         }
      }

      calcTotal();
   }

   private PDDocumentInformation addInformation(PDDocument document) {
      PDDocumentInformation pdd = document.getDocumentInformation();

      pdd.setAuthor(RecInfo.getOwner());
      pdd.setTitle(RecInfo.getTitle());
      pdd.setCreator(RecInfo.getCompany());
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