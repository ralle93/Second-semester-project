package applayer;

import datalayer.Data;

import java.time.LocalDate;


// TIL tests af pdfBox funktioner
public class ReceiptTest {
   public static void main(String[] args) {
      Order order = testOrder();
      User user = new User("test@mail.com","1234","Bent Hansen","88888888");

      new Data().createOrder(order);
      ReceiptCreator rc = new ReceiptCreator(order, user);
      System.out.println(rc.newReceipt());
   }

   private static Order testOrder() {
      Order ol = new Order(
            1,
            LocalDate.of(2017,5,31),
            "X-vej i Y-by 9001",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
      );

      Cake cake1 = new Cake(1,25,"TestKage1","Dette er en Test 1 beskrivelse");
      Cake cake2 = new Cake(2,30,"TestKage2","Dette er en Test 2 beskrivelse");
      Cake cake3 = new Cake(3,20,"TestKage3","Dette er en Test 3 beskrivelse");
      Cake cake4 = new Cake(4,40,"TestKage4","Dette er en Test 4 beskrivelse");
      Cake cake5 = new Cake(5,50,"TestKage5","Dette er en Test 5 beskrivelse");

      ol.add(new LineItem(cake1,8));
      ol.add(new LineItem(cake2,6));
      ol.add(new LineItem(cake3,12));
      ol.add(new LineItem(cake4,2));
      ol.add(new LineItem(cake5,10));

      return ol;
   }
}