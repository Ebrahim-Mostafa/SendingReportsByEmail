package Utils;

import static Utils.LoadProperties.authenticationProps;

public class AuthConstants {

    public static String MAF_HOST = authenticationProps.getProperty("mail.smtp.host");
    public static String MAF_PORT = authenticationProps.getProperty("mail.smtp.port");
    public static String MAF_AUTH = authenticationProps.getProperty("mail.smtp.auth");
    public static String STARTTLS_ENABLED = authenticationProps.getProperty("mail.smtp.starttls.enable");
    public static String SSL_TRUST = authenticationProps.getProperty("mail.smtp.ssl.trust");
    public static String MAF_SENDER_EMAIL = authenticationProps.getProperty("username");
    public static String MAF_SENDER_PASS = authenticationProps.getProperty("password");
    public static String FROM = authenticationProps.getProperty("From");
    public static String RECIPIENT_1 = authenticationProps.getProperty("Recipient_1");
    public static String RECIPIENT_2 = authenticationProps.getProperty("Recipient_2");
    public static String RECIPIENT_3 = authenticationProps.getProperty("Recipient_3");
}
