package dataLayer;

import java.util.ArrayList;

public class CustomCake {

   private int price;
   private ArrayList<Component> components;

   public CustomCake(int price, ArrayList<Component> components) {
      this.price = price;
      this.components = components;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public ArrayList<Component> getComponents() {
      return components;
   }

   public void setComponents(ArrayList<Component> components) {
      this.components = components;
   }
}
