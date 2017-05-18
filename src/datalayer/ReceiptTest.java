package datalayer;


// TIL tests af pdfBox funktioner
public class ReceiptTest {
   public static void main(String[] args) {
      OrderList orderList = testOrder();
      System.out.println(orderList.getPrice());
      //ReceiptCreator.newReceipt();
   }

   public static OrderList testOrder() {
      OrderList ol = new OrderList();

      Cake cake1 = new Cake(1,25,"TestKage1","Dette er en Test 1");
      Cake cake2 = new Cake(2,30,"TestKage2","Dette er en Test 2");
      Cake cake3 = new Cake(3,20,"TestKage3","Dette er en Test 3");

      ol.add(new Order(cake1,8,""));
      ol.add(new Order(cake2,6,"Gluten free!"));
      ol.add(new Order(cake3,12,"I want this 50% off the price"));

      return ol;
   }
}