package applayer;

class LineItem {
   private Cake cake;
   private int amount;
   private int price;
   private String notes;

   LineItem(Cake cake, int amount, String notes) {
      this.cake = cake;
      this.amount = amount;
      this.price = cake.getPrice() * amount;
      this.notes = notes;
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

   String getNotes() {
      return notes;
   }
}