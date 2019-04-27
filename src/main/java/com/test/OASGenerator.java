package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.json.JSONArray;

public class OASGenerator {

    int[][] m;
	File outfile001;
	BufferedWriter wr001;

	public OASGenerator(String wf) {
		String fileNameBase = wf + "OAS/OAS-COS";
		String fileNameExt = ".csv";
		String fileName;
		String seq;
		for (int i=1; i < 54; i++) {
			seq = Integer.toString(i);
			for (int j = seq.length(); j < 3; j++) {
				seq = "0" + seq;
			}
			fileName = fileNameBase + seq + fileNameExt;
			createOAS(fileName, seq);
		}
 	}
	public void createOAS(String fileName, String seq) {
		System.out.println("fileName: " + fileName);
		outfile001 = new File(fileName); 
		outfile001.delete();
		try {
			wr001 = new BufferedWriter(new FileWriter(outfile001));
			wr001.write("Study ID,da name,data dict,deployment uri,cell scope,owner email,permission uri\n");
			String content = "2019-SCROBSV,COS001,SDD-COS,http://hadatac.org/kb/lesa#DPL-SCR-COS_Sensor-001,\"<<*, lesa-kb:SBJ-CFOV0001-SCR>>\",pinhep@rpi.edu,Public\n";
			content = content.replaceAll("001", seq);
			wr001.write(content);
			wr001.close();
		} catch (Exception e) {
		}
	}

}
