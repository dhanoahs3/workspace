package com.retail.util;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.retail.base.BaseTest;

public class ExtentManager {
	
	static ExtentReports extent;
	
	public static ExtentReports getInstance(String path){
		
		if(extent==null){
			Date date = new  Date();
			String folderName = date.toString().replace(":","_ ").replace(" ","_");
			new File(path+folderName).mkdir();
			new File(path+folderName+"\\log").mkdir(); //create a new directory named logs 
            BaseTest.reportFolder =path+folderName; // give this new folder name to reportFolder
			String filename = path+folderName+"\\report.html";
			System.out.println("==========================================================>"+filename);
			createInstance(filename);
		}
		return extent;
		
	}
	
	public static ExtentReports createInstance(String filename){
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
		htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
		
		
	}
	

}
