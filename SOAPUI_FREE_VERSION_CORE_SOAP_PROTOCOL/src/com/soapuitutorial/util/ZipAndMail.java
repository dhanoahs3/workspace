package com.soapuitutorial.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.soapuitutorial.script.Constants;
public class ZipAndMail {
	
	
	public static void main(String[]args) throws Exception{
		
		
		
        System.out.println("to start");
        
        File repDir = new File(Constants.PROJECT_PATH+"\\SoapReporting\\xlsreports");
        File[] allFiles  = repDir.listFiles();
        for(int i=0;i<allFiles.length;i++)
        {System.out.println(allFiles[i].getName());}
        		
        
       
        
        try{
        
        File afile =new File(Constants.PROJECT_PATH+"\\SoapReporting\\xlsreports\\"+allFiles[allFiles.length-1].getName());
	    File bfile =new File(Constants.srcFolder+"\\reports.xlsx");
	    
	   // if(!bfile.exists())
	   // 	bfile.createNewFile();

	    FileInputStream inStream = new FileInputStream(afile);
	    FileOutputStream outStream = new FileOutputStream(bfile);

	    byte[] buffer = new byte[1024];
	    
	    int length;
	    //copy the file content in bytes 
	    while ((length = inStream.read(buffer)) > 0){

	    	outStream.write(buffer, 0, length);

	    }

	    inStream.close();
	    outStream.close();
		}
        
        catch(Exception e){
	    e.printStackTrace();
	    }

        
 /***********************************************************************************************************************/
		
        Zip.zipDir(Constants.srcFolder, Constants.destFolder+Constants.zipFilename);
		
		System.out.println("All done");
		String[] to={"shivdeepdhanoa@gmail.com"};

        String[] cc={};
        String[] bcc={};

        //This is for google

        SendMail.sendMail("shivdeepdhanoa@gmail.com",
        		            "haioyerabba",
        		            "smtp.gmail.com",
        		            "465",
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
        		           Constants.destFolder+Constants.zipFilename,
        		           Constants.zipFilename);
		


    	System.out.println("Email sent!!");
		
/*******************************************************************************************************************************/		
    	
    	
		/*try{
			
			File repDir = new File(Constants.PROJECT_PATH+"\\xlreports");
			File[] allFiles = repDir.listFiles();
			//for(int i=0;i<allFiles.length;i++){
			//	System.out.println(allFiles[i].getName());
			//}
			
			
			
		// copy the xls report in html reports
		File afile =new File(Constants.PROJECT_PATH+"\\xlreports\\"+allFiles[allFiles.length-1].getName());
	    File bfile =new File(Constants.srcFolder+"\\reports.xlsx");
	    
	   // if(!bfile.exists())
	   // 	bfile.createNewFile();

	    FileInputStream inStream = new FileInputStream(afile);
	    FileOutputStream outStream = new FileOutputStream(bfile);

	    byte[] buffer = new byte[1024];

	    int length;
	    //copy the file content in bytes 
	    while ((length = inStream.read(buffer)) > 0){

	    	outStream.write(buffer, 0, length);

	    }

	    inStream.close();
	    outStream.close();
		}catch(Exception e){
	    e.printStackTrace();
	    }
	    	
	    
		
		
		try {
			Zip.zipDir(Constants.srcFolder, Constants.destFolder+Constants.zipFilename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String[] to={"its.thakur@gmail.com"};

         String[] cc={};
         String[] bcc={};

         //This is for google

         SendMail.sendMail("its.thakur@gmail.com",
         		            "Heman123",
         		            "smtp.gmail.com",
         		            "465",
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
         		           Constants.destFolder+Constants.zipFilename,
         		          Constants.zipFilename);
         		          
       		          
     */
	}
	

}


// eclipse code
// ant - html report
// zip and send report
