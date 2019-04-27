package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.json.JSONArray;

public class ProcessPhysLine {

    String[] t;
    float[] tValue;
	File outfile001;
	File outfile002;
	File outfile003;
	File outfile004;
	File outfile005;
	File outfile006;
	File outfile007;
	BufferedWriter wr001;
	BufferedWriter wr002;
	BufferedWriter wr003;
	BufferedWriter wr004;
	BufferedWriter wr005;
	BufferedWriter wr006;
	BufferedWriter wr007;

	public ProcessPhysLine(String wf) {
	    t = new String[5];
	    tValue = new float[5];
		outfile001 = new File(wf + "raw-data/DA-TEMP001.csv"); 
		outfile001.delete();
		outfile002 = new File(wf + "raw-data/DA-TEMP002.csv"); 
		outfile002.delete();
		outfile003 = new File(wf + "raw-data/DA-TEMP003.csv"); 
		outfile003.delete();
		outfile004 = new File(wf + "raw-data/DA-TEMP004.csv"); 
		outfile004.delete();
		outfile005 = new File(wf + "raw-data/DA-TEMP005.csv"); 
		outfile005.delete();
		outfile006 = new File(wf + "raw-data/DA-CO2-001.csv"); 
		outfile006.delete();
		outfile007 = new File(wf + "raw-data/DA-RH001.csv"); 
		outfile007.delete();
		try {
			wr001 = new BufferedWriter(new FileWriter(outfile001));
			wr001.write("TIMESTAMP,TEMP\n");
			wr002 = new BufferedWriter(new FileWriter(outfile002));
			wr002.write("TIMESTAMP,TEMP\n");
			wr003 = new BufferedWriter(new FileWriter(outfile003));
			wr003.write("TIMESTAMP,TEMP\n");
			wr004 = new BufferedWriter(new FileWriter(outfile004));
			wr004.write("TIMESTAMP,TEMP\n");
			wr005 = new BufferedWriter(new FileWriter(outfile005));
			wr005.write("TIMESTAMP,TEMP\n");
			wr006 = new BufferedWriter(new FileWriter(outfile006));
			wr006.write("TIMESTAMP,CO2\n");
			wr007 = new BufferedWriter(new FileWriter(outfile007));
			wr007.write("TIMESTAMP,RH\n");
		} catch (Exception e) {
		}
 	}
	
	public void close() {
		try {
			wr001.close();
			wr002.close();
			wr003.close();
			wr004.close();
			wr005.close();
			wr006.close();
			wr007.close();
		} catch (Exception e) {
		}
 	}
	
	public void newLine(String timeStr, String tempStr, String rhStr, String co2Str) { 
		processTemp(timeStr, tempStr);
		processPhys(timeStr, rhStr, co2Str);
	}
 
	public void processTemp(String timeStr, String tempStr) {
		tempStr = tempStr.substring(tempStr.indexOf('('));
		tempStr = tempStr.replaceAll("\\((.+?)\\)", "$1");
		t = tempStr.split(",");
		int i = 0;
		for (String aux : t) {
			tValue[i] = Float.parseFloat(aux);
			i++;
		}
		
		try {
			wr001.write(timeStr + ", " + tValue[0] + "\n");
			wr002.write(timeStr + ", " + tValue[1] + "\n");
			wr003.write(timeStr + ", " + tValue[2] + "\n");
			wr004.write(timeStr + ", " + tValue[3] + "\n");
			wr005.write(timeStr + ", " + tValue[4] + "\n");
		} catch (Exception e) {
		}
	}

	public void processPhys(String timeStr, String rhStr, String co2Str) {
		System.out.println("PHYS: " + timeStr);
		rhStr = rhStr.replaceAll("rh: ", "");
		co2Str = co2Str.replaceAll("co2: ", "");
		try {
			Float.parseFloat(rhStr);
		} catch (Exception e) {
			rhStr = "null";
		}
		
		try {
			Float.parseFloat(co2Str);
		} catch (Exception e) {
			co2Str = "null";
		}
		
		try {
			if (!co2Str.equals("null")) {
				wr006.write(timeStr + "," + co2Str + "\n");
			}
			if (!rhStr.equals("null")) {
				wr007.write(timeStr + "," + rhStr + "\n");
			}
		} catch (Exception e) {
		}
	}

}
