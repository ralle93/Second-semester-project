package security;
import java.util.Random;

/**
 * Made by Rasmus Dreier Thrane
 * Small changes made by Søren Diamant Skovgaard
 */
// Class that generates random activation/reset keys
public class RNGString {

   private String key;
   private String link;
   private final String DOMAIN_SERVLET_URL = "http://localhost:8080/ActivateReset";
   private final String CHARS_TO_USE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

   public RNGString(String parameterName){
      this.key = generateString();
      this.link = DOMAIN_SERVLET_URL + "?" + parameterName + "=" + key;
      System.out.println(link);
   }

   private String generateString(){
      String generated = "";
      final int STRINGLENGTH = 30;

      for(int i = 0; i < STRINGLENGTH; i++){
         generated += CHARS_TO_USE.charAt(getRNG());
      }
      return generated;
   }

   private int getRNG(){
      int randomInt;
      Random r = new Random();
      randomInt = r.nextInt(CHARS_TO_USE.length());

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