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

/**
 *  Lavet af Mikkel Olsen
 */

public class ReceiptCreator {
   private PDDocument doc;
   private PDPage page;
   private Order order;
   private User user;
   private PDPageContentStream contentStream;

   /** DIMENSIONS OF PDF DOCUMENTS
    * IN PIXELS: 792 height / 612 width
    **/
   private final int BEGIN_INFO_X = 30;
   private final int BEGIN_INFO_Y = 630;
   private final int INFO_LINE_SPACE = 20;

   private final int BEGIN_ORDER_LIST_Y = 500;
   private final int ORDER_LINE_SPACE = 40;
   private final int AMOUNT_COLUMN = 300;
   private final int PRICE_COLUMN = 400;
   private final int TOTAL_COLUMN = 500;
   private final int TOTAL_LINE = RecInfo.getMaxOrderSize() * ORDER_LINE_SPACE;
   private final int VAT_LINE = TOTAL_LINE + 20;
   private final String PRICE_ENDING = ",- kr.";

   private DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
         contentStream.drawImage(pdImage, 30, 700, 280, 52);
      } catch (IOException e) {
         e.printStackTrace();
         System.out.println("ERROR: LOGO MISSING");
      }
   }

   private void addUserInfo() {
      try {
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_INFO_Y + INFO_LINE_SPACE);
         String title = "Køber Information:";
         contentStream.showText(title);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_INFO_Y);
         String name = "Navn: " + user.getName();
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_INFO_Y - INFO_LINE_SPACE);
         String address = "Addresse: " + order.getAddress();
         contentStream.showText(address);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_INFO_Y - 2 * INFO_LINE_SPACE);
         String email = "E-mail: " + user.getEmail();
         contentStream.showText(email);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_INFO_Y - 3 * INFO_LINE_SPACE);
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
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_INFO_Y + 6 * INFO_LINE_SPACE);
         String number = "Faktura Nummer: " + getID();
         contentStream.showText(number);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_INFO_Y + 5 * INFO_LINE_SPACE);
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
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_INFO_Y + 2 * INFO_LINE_SPACE);
         String title = "Firma Information:";
         contentStream.showText(title);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_INFO_Y + INFO_LINE_SPACE);
         String cvrNumber = "CVR-Nummer: " + RecInfo.getCvrNumber();
         contentStream.showText(cvrNumber);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_INFO_Y);
         String company = "Firma: " + RecInfo.getCompany();
         contentStream.showText(company);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_INFO_Y - INFO_LINE_SPACE);
         String name = "Addresse: " + RecInfo.getAddress();
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_INFO_Y - 2 * INFO_LINE_SPACE);
         String email = "E-mail: " + RecInfo.getEmail();
         contentStream.showText(email);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_INFO_Y - 3 * INFO_LINE_SPACE);
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
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_ORDER_LIST_Y + offset);
         String name = "Kage / Beskrivelse:";
         contentStream.showText(name);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_ORDER_LIST_Y + offset);
         String amount = "Personer / Antal:";
         contentStream.showText(amount);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + PRICE_COLUMN, BEGIN_ORDER_LIST_Y + offset);
         String price = "Enkelt pris:";
         contentStream.showText(price);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + TOTAL_COLUMN, BEGIN_ORDER_LIST_Y + offset);
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
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_ORDER_LIST_Y - (i * ORDER_LINE_SPACE));
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
         contentStream.newLineAtOffset(BEGIN_INFO_X, (BEGIN_ORDER_LIST_Y - 10) - (i * ORDER_LINE_SPACE));
         String text = order.getLineItem(i).getCake().getDescription();

         if (text.length() > 60) {
            int j = 50;
            while (text.charAt(j) != ' ') {
               j++;
            }
            text = text.substring(0, j);
            text += "...";
         }

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
         contentStream.newLineAtOffset(BEGIN_INFO_X + AMOUNT_COLUMN, BEGIN_ORDER_LIST_Y - (i * ORDER_LINE_SPACE));
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
         contentStream.newLineAtOffset(BEGIN_INFO_X + PRICE_COLUMN, BEGIN_ORDER_LIST_Y - (i * ORDER_LINE_SPACE));
         String text = order.getLineItem(i).getCake().getPrice() + PRICE_ENDING;
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
         contentStream.newLineAtOffset(BEGIN_INFO_X + TOTAL_COLUMN, BEGIN_ORDER_LIST_Y - (i * ORDER_LINE_SPACE));
         String text = order.getLineItem(i).getPrice() + PRICE_ENDING;
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
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_ORDER_LIST_Y - TOTAL_LINE);
         String totalText = "Total:";
         contentStream.showText(totalText);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
         contentStream.newLineAtOffset(BEGIN_INFO_X + TOTAL_COLUMN, BEGIN_ORDER_LIST_Y - TOTAL_LINE);
         String total = order.getTotalPrice() + PRICE_ENDING;
         contentStream.showText(total);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_ORDER_LIST_Y - VAT_LINE);
         String vatText = "Heraf Moms 25% :";
         contentStream.showText(vatText);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X + TOTAL_COLUMN, BEGIN_ORDER_LIST_Y - VAT_LINE);
         double calcVAT = Math.round(((double) order.getTotalPrice() * 0.25) * 100.0) / 100.0;
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
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_ORDER_LIST_Y - (40 + VAT_LINE));
         String title = "Eventuelle Noter:";
         contentStream.showText(title);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ITALIC, 12);
         contentStream.setLeading(20);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_ORDER_LIST_Y - (60 + VAT_LINE));

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
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_INFO_X + 40);
         String info = "Betaling foregår enten kontant, igennem MobilePay til: " + RecInfo.getPhone() + ", eller ved bank overførsel:";
         contentStream.showText(info);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_INFO_X + 20);
         String bankInfo = "Konto Nummer: " + RecInfo.getAccountNumber() + "     Registerings Nummer: " + RecInfo.getRegNumber();
         contentStream.showText(bankInfo);
         contentStream.endText();

         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
         contentStream.newLineAtOffset(BEGIN_INFO_X, BEGIN_INFO_X);
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
      String path = "Faktura/KageFaktura" + getID() + ".pdf";

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