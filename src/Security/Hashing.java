package Security;

public class Hashing {

   public static int hash(String str){
      return str.hashCode();
   }

   public static void main(String[] args) {
      Hashing h = new Hashing();
      System.out.println(hash("myPasswordISWeak"));
   }
}
