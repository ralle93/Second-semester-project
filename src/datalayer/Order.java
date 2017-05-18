package datalayer;

public class Order {
   private Cake cake;
   private CustomCake customCake;
   private int amount;
   private int price;
   private String notes;

   Order(Cake cake, int amount, String notes) {
      this.cake = cake;
      this.customCake = null;
      this.amount = amount;
      this.price = cake.getPrice() * amount;
      this.notes = notes;
   }

   Order(CustomCake customCake, int amount, String notes) {
      this.cake = null;
      this.customCake = customCake;
      this.amount = amount;
      this.price = customCake.getPrice() * amount;
      this.notes = notes;
   }

   int getPrice() {
      return price;
   }
}