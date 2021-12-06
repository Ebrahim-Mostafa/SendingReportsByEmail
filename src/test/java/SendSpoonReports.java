import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static Utils.AuthConstants.*;
import static Utils.LoadProperties.authenticationProps;

public class SendSpoonReports {

    public static void sendSpoonReport () {
        // Create object of Property file
//        Properties props = new Properties();
        authenticationProps.put("mail.smtp.host", MAF_HOST);
        authenticationProps.put("mail.smtp.port", MAF_PORT);
        authenticationProps.put("mail.smtp.auth", MAF_AUTH);
        authenticationProps.put("mail.smtp.starttls.enable", STARTTLS_ENABLED);
        authenticationProps.put("mail.smtp.ssl.trust", SSL_TRUST);

        // This will handle the complete authentication
        Session session = Session.getDefaultInstance(authenticationProps,

                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(MAF_SENDER_EMAIL, MAF_SENDER_PASS);

                    }

                });

        try {

            // Create object of MimeMessage class
            Message message = new MimeMessage(session);
            // Set the from address
            message.setFrom(new InternetAddress(FROM));
            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECIPIENT_2));//To
            message.setSubject("Spoon Automation Test Report");

            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();
            String messageBody = "Kindly have a look at the latest test automation report execution attached.";
            String htmlText = "<font size =\"4\" face=\"arial\" >Dear all,</font>" + "<br><br>" + "<font size =\"4\" face=\"arial\" >" + messageBody + "</font>" + "<br><br>" + "<font size =\"4\" face=\"arial\"> https://security-dashboard-spoon-report.netlify.app/ </font>"+"</font>" + "<br><br>" + "<font size =\"4\" face=\"arial\" >Best Regards,</font>";
            messageBodyPart1.setContent(htmlText, "text/html");

            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            String fileLocation = CompressFile.SPOON_OUTPUT_ZIP_FILE;

            // Create data source and pass the filename
            DataSource source = new FileDataSource(fileLocation);

            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));

            // set the file
            messageBodyPart2.setFileName("Spoon_Automation_Report.zip");
//            messageBodyPart2.attachFile(fileLocation);

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            // add body part 1
            multipart.addBodyPart(messageBodyPart2);

            // add body part 2
            multipart.addBodyPart(messageBodyPart1);

            // set the content
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);

            System.out.println("===== Email Sent Successfully with Spoon Report!! =====");

        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }
    }
}
