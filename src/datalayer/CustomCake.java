package datalayer;

import java.util.ArrayList;

public class CustomCake {

   private int price;
   private ArrayList<Component> components;

   public CustomCake(int price, ArrayList<Component> components) {
      this.price = price;
      this.components = components;
   }

   public int getPrice() {
      int price = 0;

      for (int i = 0; i < components.size() - 1; i++) {
         price += components.get(i).getPrice();
      }

      return price;
   }

   public ArrayList<Component> getComponents() {
      return components;
   }

   public void setComponents(ArrayList<Component> components) {
      this.components = components;
   }
}
