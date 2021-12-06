import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static Utils.AuthConstants.*;
import static Utils.LoadProperties.authenticationProps;

public class SendAutomationReportLink {

    //    public static void sendAutomationReportUsingURL () {
    public static void main(String[] args) {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECIPIENT_1));//To
            message.setSubject("Android Automation Execution Test Report");

            String messageBody = "Kindly, have a look at the latest test automation report execution attached in the following Link:";
            String reportURL = " https://security-dashboard-spoon-report.netlify.app/";
            String htmlText = "<font size =\"4\" face=\"arial\" >Dear all,</font>" + "<br><br>" + "<font size =\"4\" face=\"arial\" >" + messageBody + "</font>" + "<br><br>" + "<font size =\"4\" face=\"arial\">" + reportURL + "</font>"+"</font>" + "<br><br>" + "<font size =\"4\" face=\"arial\" >Thanks & Regards,</font>";
            message.setContent(htmlText, "text/html");
            Transport.send(message);

            System.out.println("===== Email Sent Successfully with Android Automation Report !! =====");

        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }
    }
}
