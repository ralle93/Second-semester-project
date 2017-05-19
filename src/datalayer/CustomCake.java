package datalayer;

import java.util.ArrayList;


public class CustomCake {
   private int price;
   private ArrayList<Component> components;

   public CustomCake(ArrayList<Component> components) {
      this.price = 0;

      for (int i = 0; i < components.size() - 1; i++) {
         price += components.get(i).getPrice();
      }

      this.components = components;
   }

   public int getPrice() {
      return price;
   }

   public ArrayList<Component> getComponents() {
      return components;
   }

   public void setComponents(ArrayList<Component> components) {
      this.components = components;
   }

   public String getComponentsNames() {
      String s = "";

      for (int i = 0; i < components.size(); i++) {
         if (i != components.size() - 1) {
            s += components.get(i) + ", ";
         }
      }

      return s;
   }
}