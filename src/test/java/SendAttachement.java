import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

import static Utils.AuthConstants.*;
import static Utils.LoadProperties.authenticationProps;

public class SendAttachement {

    public static void sendReport(){
        // Create object of Property file
        authenticationProps.put("mail.smtp.host", MAF_HOST);
        authenticationProps.put("mail.smtp.port", MAF_PORT);
        authenticationProps.put("mail.smtp.auth", MAF_AUTH);
        authenticationProps.put("mail.smtp.starttls.enable", STARTTLS_ENABLED);
        authenticationProps.put("mail.smtp.ssl.trust", SSL_TRUST);

        // This will handle the complete authentication
        Session sessionn = Session.getDefaultInstance(authenticationProps,

                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(MAF_SENDER_EMAIL, MAF_SENDER_PASS);

                    }

                });

        try {

            // Create object of MimeMessage class
            Message message = new MimeMessage(sessionn);
            // Set the from address
            message.setFrom(new InternetAddress(FROM));
            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECIPIENT_1));//To
            message.setSubject("Automation Testing Reports");

            // Create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("Hello Hema");

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Second part is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "/Users/imostafa/Downloads/spoon-output.zip";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("EMail Sent Successfully with attachment!!");
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
