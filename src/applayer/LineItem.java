package applayer;

public class LineItem {
   private Cake cake;
   private int amount;
   private int price;

   public LineItem(Cake cake, int amount) {
      this.cake = cake;
      this.amount = amount;
      this.price = cake.getPrice() * amount;
   }

   Cake getCake() {
      return cake;
   }

   int getPrice() {
      return price;
   }

   int getAmount() {
      return amount;
   }
}