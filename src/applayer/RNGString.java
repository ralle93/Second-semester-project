package applayer;
import java.util.Random;

public class RNGString {

   private String key;
   private String link;
   private final String DOMAIN_SERVLET_URL = "http://localhost:8080/ActivateReset";
   private final String charsToUse = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

   public static void main(String[] args) {
      /*RNGString rng = new RNGString("Activation", "link");

      SendGmail.sendToCustomer("mikk7506@stud.kea.dk","Activation Mail", rng.getLink());*/
   }
   public RNGString(String parameterName){
      this.key = generateString();
      this.link = DOMAIN_SERVLET_URL + "?" + parameterName + "=" + key;
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

      if(randomInt == 0 ) return randomInt;

      return randomInt -1;
   }

   public String getKey() {
      return key;
   }

   public String getLink() {
      return link;
   }

}
