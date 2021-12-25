package rough;

import com.soapuitutorial.util.SendMail;
import com.soapuitutorial.util.Zip;

public class Mail_Zip_Example {

	public static void main(String[] args) throws Exception {
		String srcFolder="C:\\testing\\Soap_Protocol\\SoapReporting\\xlsreports";
		String destFolder="C:\\testing\\Soap_Protocol\\zipfiles\\";
		String zipFilename="htmlreport.zip";
		
		System.out.println("to start");

		
		Zip.zipDir(srcFolder, destFolder+zipFilename);
		
		System.out.println("All done");
		String[] to={"dhanoahs3@gmail.com"};

        String[] cc={};
        String[] bcc={};

        //This is for google

        SendMail.sendMail("ronykhurd@gmail.com",
        		            "Notebook1",
        		            "smtp.gmail.com",
        		            "587",
        		            "true",
        		            "true",
        		            true,
        		            "javax.net.ssl.SSLSocketFactory",
        		            "false",
        		            to,
        		            cc,
        		            bcc,
        		            "WebService test Reports",
        		            "Please find the reports attached.\n\n Regards\nWebMaster",
        		           destFolder+zipFilename,
        		           zipFilename);
		


    	System.out.println("Email sent!!");

	}
	


}
