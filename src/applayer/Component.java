package applayer;

class Component {
   private int id;
   private String name;
   private String type;
   private int price;

   Component(int id, String name, String type, int price) {
      this.id = id;
      this.name = name;
      this.type = type;
      this.price = price;
   }

   int getId() {
      return id;
   }

   String getName() {
      return name;
   }

   String getType() {
      return type;
   }

   int getPrice() {
      return price;
   }
}