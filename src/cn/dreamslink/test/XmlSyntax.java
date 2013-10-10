package cn.dreamslink.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class XmlSyntax {
	public static void main(String[] args) {
		String demo = "/home/doris/scripts/test.xml";
		try {
			String demoString = readFromFile(demo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
    private static String readFromFile(String filePaht) throws Exception{
    	File file = new File(filePaht);
    	BufferedReader in = new BufferedReader(new FileReader(file));
    	String result = "";
    	String temp;
    	while ((temp = in.readLine()) != null) {
    		result = result + temp;
    	}
    	return result;	
    }
}
