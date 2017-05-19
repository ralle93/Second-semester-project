package applayer;

public class Component {
   private int price;
   private String name;

   public Component(int price, String name) {
      this.price = price;
      this.name = name;
   }

   public int getPrice() {
      return price;
   }

   public String getName() {
      return name;
   }
}