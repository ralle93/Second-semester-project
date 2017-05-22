package Security;

public class Hash {

   public static String hashPW(String str){
      return str.hashCode() + "";
   }

   public static void main(String[] args) {
      System.out.println(hashPW("test123"));
   }
}
