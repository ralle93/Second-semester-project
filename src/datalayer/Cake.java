package datalayer;

public class Cake {

   private int id;
   private int price;
   private String name;
   private String description;

   public Cake(int id, int price, String name, String description) {
      this.id = id;
      this.price = price;
      this.name = name;
      this.description = description;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
