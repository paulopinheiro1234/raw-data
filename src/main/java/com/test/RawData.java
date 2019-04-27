package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RawData {

	public static void main(String args[]) throws Exception {
		String workingFolder = "/Users/paulo/Documents/research/LESA/data/";
		processTOF(workingFolder);
		processCOS(workingFolder);
		//processPhys(workingFolder);
		//processWeather(workingFolder);
		cosOAS(workingFolder);
	}     

	public static void processTOF(String wf) throws IOException  {
		  File infile = new File(wf + "raw-data/tof.txt"); 

		  ProcessTOFLine pl = new ProcessTOFLine(wf);
		  
		  BufferedReader br = new BufferedReader(new FileReader(infile)); 
		     
		  String str; 
		  String timeStr = ""; 
		  while ((str = br.readLine()) != null) { 
		    if (!str.startsWith("[[")) {
		    	timeStr = "2019-04-17T" + str + "Z";
		    } else {
		    	//str = str.replaceAll(",", ";");
			    System.out.println(timeStr);
		    	pl.newLine(timeStr, str);
		    }
		  } 
		  
		  pl.close();
	}     

	public static void processCOS(String wf) throws IOException  {
		  File infile = new File(wf + "raw-data/cos.txt"); 

		  ProcessCOSLine pl = new ProcessCOSLine(wf);
		  
		  BufferedReader br = new BufferedReader(new FileReader(infile)); 
		     
		  String str; 
		  String timeStr = ""; 
		  while ((str = br.readLine()) != null) { 
		    if (!str.startsWith("[[")) {
		    	timeStr = "2019-04-17T" + str + "Z";
		    } else {
		    	//str = str.replaceAll(",", ";");
			    System.out.println(timeStr);
		    	pl.newLine(timeStr, str);
		    }
		  } 
		  
		  pl.close();
	}     

	public static void processPhys(String wf) throws IOException  {
		  File infile = new File(wf + "raw-data/hvac.txt"); 

		  ProcessPhysLine pl = new ProcessPhysLine(wf);
		  
		  BufferedReader br = new BufferedReader(new FileReader(infile)); 
		     
		  String str; 
		  String timeStr = null; 
		  String tempStr = null; 
		  String rhStr = null; 
		  String co2Str = null; 
		  while ((str = br.readLine()) != null) { 
			if (str.equals("")) {
			} else if (str.startsWith("temp:")) {
				tempStr = str; 
			} else if (str.startsWith("rh:")) {
				rhStr = str; 
			} else if (str.startsWith("co2:")) {
				co2Str = str; 
		    } else {
		    	timeStr = "2019-04-17T" + str + "Z";
		    }
			if (tempStr != null && rhStr != null && co2Str != null && timeStr != null) {
				pl.newLine(timeStr,tempStr,rhStr,co2Str);
				tempStr = null;
				rhStr = null;
				co2Str = null;
				timeStr = null;
			}
		  } 
		  
		  pl.close();
	}     

	public static void processWeather(String wf) throws IOException  {
		  File infile = new File(wf + "raw-data/weather.txt"); 

		  ProcessWeatherLine pl = new ProcessWeatherLine(wf);
		  
		  BufferedReader br = new BufferedReader(new FileReader(infile)); 
		     
		  String str; 
		  String timeStr = null; 
		  String tempStr = null; 
		  String rhStr = null; 
		  String pressureStr = null; 
		  while ((str = br.readLine()) != null) { 
			if (str.equals("")) {
			} else if (str.startsWith("temp:")) {
				tempStr = str; 
			} else if (str.startsWith("humidity:")) {
				rhStr = str; 
			} else if (str.startsWith("pressure:")) {
				pressureStr = str; 
			} else if (str.startsWith("status:")) {
		    } else {
		    	timeStr = "2019-04-17T" + str + "Z";
		    }
			if (tempStr != null && rhStr != null && pressureStr != null && timeStr != null) {
				pl.newLine(timeStr,tempStr,rhStr,pressureStr);
				tempStr = null;
				rhStr = null;
				pressureStr = null;
				timeStr = null;
			}
		  } 
		  
		  pl.close();
	}     

	public static void cosOAS(String wf) throws IOException  {
		  OASGenerator oas = new OASGenerator(wf);
	}     

}

