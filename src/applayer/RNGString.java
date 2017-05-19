package applayer;
import java.util.Random;

public class RNGString {
   private String link;
   private final String charsToUse = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

   public static void main(String[] args) {
      RNGString rng = new RNGString("Activation", "link");

      SendGmail.sendToCustomer("mikk7506@stud.kea.dk","Activation Mail", rng.getLink());
   }
   public RNGString(String servlet, String parameterName){
      this.link = "localhost:8080/" + servlet + "?" + parameterName + "=" + generateString();
      System.out.println(link);
   }

   private String generateString(){
      String generated = "";
      final int STRINGLENGTH = 30;

      for(int i = 0; i < STRINGLENGTH; i++){
         generated += charsToUse.charAt(getRNG());
      }
      return generated;
   }

   private int getRNG(){
      int randomInt = 0;
      Random r = new Random();
      randomInt = r.nextInt(charsToUse.length());

      if(randomInt -1 == -1 ) return randomInt;

      return randomInt -1;
   }

   public String getLink() {
      return link;
   }

}
