package security;

public class Hash {
   public static String hashPW(String str){
      return str.hashCode() + "";
   }
}