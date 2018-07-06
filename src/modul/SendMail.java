package modul;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * This class will send an email
 * Currently not used but do not delete it can be used later
 * 
 * @author GiBr03
 *
 */
public class SendMail {

	/**
	 * Sends an email Note: this can only be send over an gmail account an if your
	 * account allows access from less secure apps 
	 * Read -> https://serverfault.com/questions/635139/how-to-fix-send-mail-authorization-failed-534-5-7-14
	 * If you want to go over your own email-server or an localhost 
	 * Read -> https://www.tutorialspoint.com/java/java_sending_email.htm
	 * 
	 * @param content
	 *            content of your mail
	 * @param address
	 *            to whom this should be send. This can be the users email address
	 *            which can be grabbed from the xml file or your own for ie error
	 *            reports
	 */
	public void sendMailGmail(String content, String address) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("emailaddress", "password"); // TODO: add your information
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("emailadress"));// TODO: add your email address from line 21
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
			message.setSubject("Test 123");
			message.setText(content);
			Transport.send(message);

			System.out.println("Email send");
		} catch (Exception e) {
			System.out.println(">>>Error while sending Email: " + e);
		}
	}

}
