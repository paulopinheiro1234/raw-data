package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.json.JSONArray;

public class ProcessCOSLine {

    int[][] m;
	File[] outfile;
	BufferedWriter[] wr;

	public ProcessCOSLine(String wf) {
	    m = new int[57][6];
	    outfile = new File[57];
	    wr = new BufferedWriter[57];
	    for (int i=0; i < 53; i++) {
	    	String seq = Integer.toString(i + 1);
			for (int j = seq.length(); j < 3; j++) {
				seq = "0" + seq;
			}
	    	outfile[i] = new File(wf + "raw-data/DA-COS" + seq + ".csv"); 
			outfile[i].delete();
			try {
				wr[i] = new BufferedWriter(new FileWriter(outfile[i]));
				wr[i].write("TIMESTAMP,RGBLUM\n");
			} catch (Exception e) {
			}
	    }
 	}
	
	public void close() {
	    for (int i=0; i < 53; i++) {
	    	try {
	    		wr[i].close();
	    	} catch (Exception e) {
	    	}
	    }
 	}
	
	public void newLine(String timeStr, String str) { 
		JSONArray jsArray = new JSONArray(str);
		System.out.println(str + "\n\n");
		
		for (int i=0; i < jsArray.length(); i++) {
			System.out.println("\nget(i): "  + jsArray.get(i).toString());
			JSONArray row = new JSONArray(jsArray.get(i).toString());
			for (int j=0; j < row.length(); j++) {
				System.out.print("[" + i + "," + j + "]: " + row.get(j).toString());
				m[i][j] = Integer.parseInt(row.get(j).toString());	
			}	
			try {
				wr[i].write(timeStr + ", " + printCOS(i) + "\n");
			} catch (Exception e) {
			}
		}
	}
 
	public String printCOS(int i) {
		String resp = "[";
		for (int j = 0; j < 4; j++) {
			resp = resp + m[i][j];
			if (j < 3) {
				resp = resp + ";";
			}
		}
		resp = resp + "]";
		return resp;

	}

}
