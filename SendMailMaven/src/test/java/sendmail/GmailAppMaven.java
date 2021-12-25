package sendmail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class GmailAppMaven {
	
	public static  void sendMail(String recipient) throws Exception{
		
		System.out.println("-------------------Perparing to send Email-------------------------");


		Properties properties = new Properties();
		properties.put("mail.smtp.auth","true"); //Do we need authentication to send email. For gmail set to true
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		
		
		final String myAccount = "ronykhurd@gmail.com";
		final String myPassword = "Notebook1";
		
		Session session = Session.getInstance(properties,new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(myAccount,myPassword);
				}
			});
		
		
		Message message  = prepareMessage(session,myAccount,recipient);
		Transport.send(message);
		System.out.println("-----------------Message sent successfully-------------------------");
	}
	
	public static Message prepareMessage(Session session,String myAccount,String recipient){
        MimeMessage message = new MimeMessage(session);
        try {
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			message.setSubject("Sending my first message from gmail");
			//message.setText("This is the content \n Check it out");
			String htmlText  = "<h1> This is message sent using java </h1> </br> <h2><em>This is second line which is italic</em></h2>";
			message.setContent(htmlText,"text/html");

			return message;
		} catch (MessagingException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
		
	}
		

}
