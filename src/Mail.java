import javax.mail.Session;
import java.util.Properties;

/**
 * Created by rasmusthrane on 15/05/17.
 */
public class Mail {
   private String to;
   private String from;
   private String content;
   private String subject;
   Properties props;
   Session sesh;


   public Mail(String to, String from, String content, String subject){
      this.to = to;
      this.from = from;
      this.content = content;
      this.subject = subject;
      props = new Properties();
      sesh =  Session.getDefaultInstance(props);
   }

   public void mailToCustomer(){

   }


   public static void main(String[] args) {

   }

}
