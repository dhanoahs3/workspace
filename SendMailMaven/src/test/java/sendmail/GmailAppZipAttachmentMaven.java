package sendmail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class GmailAppZipAttachmentMaven {
	
	public static File zipFiles() throws Exception{
		String srcFolder="C:\\testing\\Soap_Protocol\\SoapReporting\\xlsreports";
		String destFolder="C:\\testing\\Soap_Protocol\\zipfiles\\";
		String zipFilename="htmlreport2.zip";
		
		System.out.println("to start");

		
		Zip.zipDir(srcFolder, destFolder+zipFilename);
		
		 File htmlreport = new File(destFolder+zipFilename);
		 System.out.println("All done");
		 return htmlreport;
	}
	
	public static  void sendMail(String recipient,File report) throws Exception{
		
		System.out.println("------Perparing to send Email with attachment in maven project------");


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
		
		
		Message message  = prepareMessage(session,myAccount,recipient,report);
		Transport.send(message);
		System.out.println("-----------------Message sent successfully-------------------------");
	}
	
	
	
	
	public static Message prepareMessage(Session session,String myAccount,String recipient,File report) throws IOException{
        MimeMessage message = new MimeMessage(session);
        try {
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			message.setSubject("Sending my first message with attachment from gmail in maven project");
			
			String htmlText  = "<h1> This is message sent using java </h1> </br> <h2><em>This is second line of message with attachment which is italic</em></h2>";
            MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(htmlText,"text/html");
			
			
			 MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			// >C:\testing\Soap_Protocol\zipfiles\htmlreport.zip
			 attachmentBodyPart.attachFile(report);
			 
			 MimeMultipart multiPart = new MimeMultipart();
			 multiPart.addBodyPart(bodyPart);
			 multiPart.addBodyPart(attachmentBodyPart);
			 message.setContent(multiPart);

			return message;
		} catch (MessagingException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
		
	}

}
