package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.json.JSONArray;

public class ProcessWeatherLine {

    String[] t;
    float[] tValue;
	File outfile001;
	BufferedWriter wr001;

	public ProcessWeatherLine(String wf) {
	    t = new String[5];
	    tValue = new float[5];
		outfile001 = new File(wf + "raw-data/DA-WEA001.csv"); 
		outfile001.delete();
		try {
			wr001 = new BufferedWriter(new FileWriter(outfile001));
			wr001.write("TIMESTAMP,TEMP,RH,PRESSURE\n");
		} catch (Exception e) {
		}
 	}
	
	public void close() {
		try {
			wr001.close();
		} catch (Exception e) {
		}
 	}
	
	public void newLine(String timeStr, String tempStr, String rhStr, String pressureStr) { 
		System.out.println("WEATHER: " + timeStr);
		tempStr = tempStr.replaceAll("temperature: ", "");
		rhStr = rhStr.replaceAll("humidity: ", "");
		pressureStr = pressureStr.replaceAll("pressure: ", "");
		try {
			Float.parseFloat(tempStr);
		} catch (Exception e) {
			tempStr = "null";
		}
		try {
			Float.parseFloat(rhStr);
		} catch (Exception e) {
			rhStr = "null";
		}
		try {
			Float.parseFloat(pressureStr);
		} catch (Exception e) {
			pressureStr = "null";
		}
		
		try {
			wr001.write(timeStr + "," + tempStr + "," + rhStr +  "," + pressureStr + "\n");
		} catch (Exception e) {
		}
	}

}
