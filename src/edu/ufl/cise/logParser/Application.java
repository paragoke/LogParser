package edu.ufl.cise.logParser;


import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Application {

	/**
	 * @param args log path
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String path = args[0];
		
		CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Shreyas\\Documents\\GitHub\\LogParser\\Supervisor"),'\t');
	    List<String[]> logs = reader.readAll();
	    
	    Iterator<String[]> logitr = logs.iterator();
	    
	    System.out.println(logitr.next());

	}

}
