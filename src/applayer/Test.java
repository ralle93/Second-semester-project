package applayer;

public class Test {

   public static void main(String[] args) {
      UserController uc = new UserController();

      uc.validateUserInfo("a@a.dk", "test", "a b c", "12345678");
   }
}
