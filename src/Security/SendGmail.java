
package Security;
import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class SendGmail {

   final static String username = "pernilleslaekkerier@gmail.com";
   final static String password = "Abcdefg123456789";
   public static void sendToCustomer(String recipientEmail, String title, String message){
      try {
         Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
         Session session = Session.getInstance(getProperties(), null);

         final MimeMessage msg = new MimeMessage(session);

         msg.setFrom(new InternetAddress(username));
         msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

         msg.setSubject(title);
         msg.setText(message, "utf-8");
         msg.setSentDate(new Date());

         SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

         t.connect("smtp.gmail.com", username, password);
         t.sendMessage(msg, msg.getAllRecipients());
         t.close();
      } catch(MessagingException ex ){
         ex.printStackTrace();
      }
   }

   public static void sendWithAttach( String recipientEmail, String title, String message, String pdfFilePath) {
      try {
         Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
         Session session = Session.getInstance(getProperties(), null);

         //create msg, msgBody object and set subject, recipient
         final Message msg = new MimeMessage(session);
         msg.setFrom(new InternetAddress(username));
         msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
         msg.setSubject(title);
         BodyPart msgBody = new MimeBodyPart();
         msgBody.setText(message);

         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(msgBody);

         // create the attachment and set it to the email
         msgBody = new MimeBodyPart();
         DataSource src = new FileDataSource(pdfFilePath);
         msgBody.setDataHandler(new DataHandler(src));
         msgBody.setFileName(pdfFilePath);
         multipart.addBodyPart(msgBody);
         msg.setContent(multipart);

         SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

         t.connect("smtp.gmail.com", username, password);
         t.sendMessage(msg, msg.getAllRecipients());
         t.close();
      }catch(MessagingException ex){
         ex.printStackTrace();
      }
   }

   private static Properties getProperties(){
      Properties props = System.getProperties();
      props.setProperty("mail.smtps.host", "smtp.gmail.com");
      props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.setProperty("mail.smtp.socketFactory.fallback", "false");
      props.setProperty("mail.smtp.port", "465");
      props.setProperty("mail.smtp.socketFactory.port", "465");
      props.setProperty("mail.smtps.auth", "true");

      props.put("mail.smtps.quitwait", "false");

      return props;
   }
}