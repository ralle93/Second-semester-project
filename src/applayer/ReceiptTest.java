package applayer;


// TIL tests af pdfBox funktioner
public class ReceiptTest {
   public static void main(String[] args) {
      Order order = testOrder();
      User user = new User("test@mail.com","1234","Bent Hansen","88888888");

      ReceiptCreator rc = new ReceiptCreator(order, user);
      rc.newReceipt();
   }

   private static Order testOrder() {
      Order ol = new Order();

      Cake cake1 = new Cake(1,25,"TestKage1","Dette er en Test 1 beskrivelse");
      Cake cake2 = new Cake(2,30,"TestKage2","Dette er en Test 2 beskrivelse");
      Cake cake3 = new Cake(3,20,"TestKage3","Dette er en Test 3 beskrivelse");
      Cake cake4 = new Cake(4,40,"TestKage4","Dette er en Test 4 beskrivelse");
      Cake cake5 = new Cake(5,50,"TestKage5","Dette er en Test 5 beskrivelse");

      ol.add(new LineItem(cake1,8,"Test Note 1"));
      ol.add(new LineItem(cake2,6,"Gluten free!"));
      ol.add(new LineItem(cake3,12,"Test Note 3"));
      ol.add(new LineItem(cake4,2,"Test Note 4"));
      ol.add(new LineItem(cake5,10,"I want this 50% off the price"));

      return ol;
   }
}