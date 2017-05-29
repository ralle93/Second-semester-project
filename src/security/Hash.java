package security;

// Class that handles hashing passwords
public class Hash {
   public static String hashPW(String str){
      return str.hashCode() + "";
   }
}