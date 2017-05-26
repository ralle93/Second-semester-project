package applayer;

/**
 *  Lavet af Mikkel Olsen
 */

public class LineItem {
   private Cake cake;
   private int amount;
   private int price;

   public LineItem(Cake cake, int amount) {
      this.cake = cake;
      this.amount = amount;
      this.price = cake.getPrice() * amount;
   }

   public Cake getCake() {
      return cake;
   }

   public int getPrice() {
      return price;
   }

   public int getAmount() {
      return amount;
   }

   void setAmount(int amount) {
      this.amount = amount;
      this.price = cake.getPrice() * amount;
   }
}