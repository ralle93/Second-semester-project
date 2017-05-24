package applayer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class ReceiptCreator {
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
   private int totalLine = RecInfo.getMaxOrderSize() * orderLineSpace;
   private int vatLine = totalLine + 20;

   private DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
   private String priceEnding = ",- kr.";

   public ReceiptCreator(Order order, User user) {
      doc = new PDDocument();
      page = new PDPage();
      doc.addPage(page);

      this.order = order;
      this.user = user;
   }

   public String newReceipt() {
      String path = "";

      try {
         contentStream = new PDPageContentStream(doc, page);
         formatData();
         formatOrder();
         contentStream.close();
         path = saveDocument();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return path;
   }

   private void addLogo() {
      try {
         PDImageXObject pdImage = PDImageXObject.createFromFile(RecInfo.getLogoPath(), doc);
         contentStream.drawImage(pdImage, 30, 670, 240, 100);
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("ERROR: LOGO MISSING");
      }
   }

   private void addUserInfo() {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
         contentStream.newLineAtOffset(beginInfoX, beginInfoY + infoLineSpace);
         String title = "Køber Information:";
         contentStream.showText(title);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX,beginInfoY );
         String name = "Navn: " + user.getName();
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX,beginInfoY - infoLineSpace);
         String address = "Addresse: " + order.getAddress();
         contentStream.showText(address);
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

   private String getID() {
      int number = order.getOrderID();
      return String.format("%06d", number);
   }

   private void addReceiptInfo() {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY + 6 * infoLineSpace);
         String number = "Faktura Nummer: " + getID();
         contentStream.showText(number);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY + 5 * infoLineSpace);
         String date = "Faktura Udstedt: " + (LocalDate.now()).format(form);
         contentStream.showText(date);
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
         String cvrNumber = "CVR-Nummer: " + RecInfo.getCvrNumber();
         contentStream.showText(cvrNumber);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY);
         String company = "Firma: " + RecInfo.getCompany();
         contentStream.showText(company);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY - infoLineSpace);
         String name = "Addresse: " + RecInfo.getAddress();
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY - 2 * infoLineSpace);
         String email = "E-mail: " + RecInfo.getEmail();
         contentStream.showText(email);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX + amountColumn,beginInfoY  - 3 * infoLineSpace);
         String phone = "Telefon: +45 " + RecInfo.getPhone();
         contentStream.showText(phone);
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
      addReceiptInfo();
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

   private ArrayList<String> readNote() {
      int noteLineLength = 100;
      int noteMaxLength = 500;
      int length = order.getNote().length();
      ArrayList<String> arr = new ArrayList<>();

      for (int i = 0; i < length; i += noteLineLength) {
         arr.add(order.getNote().substring(i, Math.min(length, i + noteLineLength)));
         if (i > noteMaxLength) {
            return arr;
         }
      }

      return arr;
   }

   private void noteField() {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX, beginOrderListY - (40 + vatLine));
         String title = "Eventuelle Noter:";
         contentStream.showText(title);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
         contentStream.setLeading(20);
         contentStream.newLineAtOffset(beginInfoX, beginOrderListY - (60 + vatLine));

         ArrayList<String> list = readNote();

         for (int i = 0; i < list.size() && i < 5; i++) {
            String text = list.get(i);
            contentStream.showText(text);
            contentStream.newLine();
         }

         contentStream.endText();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private void PaymentInfo() {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX, beginInfoX + 40);
         String info = "Betaling foregår enten kontant, igennem MobilePay til: " + RecInfo.getPhone() + ", eller ved bank overførsel:";
         contentStream.showText(info);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(beginInfoX, beginInfoX + 20);
         String bankInfo = "Konto Nummer: " + RecInfo.getAccountNumber() + "     Registerings Nummer: " + RecInfo.getRegNumber();
         contentStream.showText(bankInfo);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
         contentStream.newLineAtOffset(beginInfoX, beginInfoX);
         String date = "Levering / afhentnings dato samt seneste betalingsdato: " + (order.getDeliveryDate()).format(form);
         contentStream.showText(date);
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
      noteField();
      PaymentInfo();
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

   private String saveDocument() {
      String path = "data/Kvitteringer/KageKvittering" + getID() + ".pdf";

      try {
         doc.setDocumentInformation(addInformation(doc));
         doc.save(path);
         doc.close();
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("ERROR: FILE ERROR");
      }

      return path;
   }
}