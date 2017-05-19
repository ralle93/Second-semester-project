package datalayer;


// TIL tests af pdfBox funktioner
public class ReceiptTest {
   public static void main(String[] args) {
      OrderList orderList = testOrder();
      User user = new User("test@mail.com","1234","Bent Hansen","88888888");

      ReceiptCreator rc = new ReceiptCreator(orderList, user);
      rc.newReceipt();
   }

   private static OrderList testOrder() {
      OrderList ol = new OrderList();

      Cake cake1 = new Cake(1,25,"TestKage1","Dette er en Test 1");
      Cake cake2 = new Cake(2,30,"TestKage2","Dette er en Test 2");
      Cake cake3 = new Cake(3,20,"TestKage3","Dette er en Test 3");
      Cake cake4 = new Cake(4,40,"TestKage4","Dette er en Test 4");
      Cake cake5 = new Cake(5,50,"TestKage5","Dette er en Test 5");

      ol.add(new Order(cake1,8,""));
      ol.add(new Order(cake2,6,"Gluten free!"));
      ol.add(new Order(cake3,12,""));
      ol.add(new Order(cake4,2,""));
      ol.add(new Order(cake5,10,"I want this 50% off the price"));

      return ol;
   }
}