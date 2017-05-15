package datalayer;

public class Component {

   private int ingredientID;
   private String bottom;
   private String frosting;

   public Component(int ingredientID, String bottom, String frosting) {
      this.ingredientID = ingredientID;
      this.bottom = bottom;
      this.frosting = frosting;
   }

   public int getIngredientID() {
      return ingredientID;
   }

   public void setIngredientID(int ingredientID) {
      this.ingredientID = ingredientID;
   }

   public String getBottom() {
      return bottom;
   }

   public void setBottom(String bottom) {
      this.bottom = bottom;
   }

   public String getFrosting() {
      return frosting;
   }

   public void setFrosting(String frosting) {
      this.frosting = frosting;
   }
}
