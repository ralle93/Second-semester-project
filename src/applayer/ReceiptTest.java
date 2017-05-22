package applayer;

import java.util.Date;


// TIL tests af pdfBox funktioner
public class ReceiptTest {
   public static void main(String[] args) {
      Order order = testOrder();
      User user = new User("test@mail.com","1234","Bent Hansen","88888888");

      new ReceiptCreator(order, user);
   }

   private static Order testOrder() {
      Order ol = new Order(new Date(2017,5,31),"");

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