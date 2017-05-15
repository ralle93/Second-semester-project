import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
   private String host = "localhost";

   public Mail(){}

   public Mail(String to, String from, String content, String subject){
      this.to = to;
      this.from = from;
      this.content = content;
      this.subject = subject;
      props = System.getProperties();
      props.setProperty("mail.smtp.host", host);
      sesh =  Session.getDefaultInstance(props);
   }




   public static void main(String[] args) {

   }

   public String getTo() {
      return to;
   }

   public void setTo(String to) {
      this.to = to;
   }

   public String getFrom() {
      return from;
   }

   public void setFrom(String from) {
      this.from = from;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }
}
