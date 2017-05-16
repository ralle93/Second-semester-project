package datalayer;

      import org.apache.pdfbox.pdmodel.PDDocument;
      import org.apache.pdfbox.pdmodel.PDPage;

      import java.io.IOException;

public class ReceiptCreator {

   // TIL tests af pdfBox funktioner
   public static void main(String[] args) throws IOException {
      PDDocument document = new PDDocument();

      PDPage blankpage = new PDPage();
      document.addPage(blankpage);

      document.save("pdf_examples/my_doc.pdf");
      System.out.println("PDF created!");

      document.close();
   }
}