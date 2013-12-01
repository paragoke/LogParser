package edu.ufl.cise.logParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import au.com.bytecode.opencsv.CSVReader;

public class CSVParser {
	
	CSVReader reader = null;
	ConnectionManager cm = new ConnectionManager();
	File folder = null;
	File[] listOfFiles = null;
	String logpath = null; 
    
	CSVParser(final String path) throws FileNotFoundException, SQLException{
		
		logpath = path;
		folder = new File(path);
		listOfFiles = folder.listFiles();
	}
	
	public String[] getNextLine() throws IOException{
		String [] nextLine;
	
		if ((nextLine = reader.readNext()) != null) {
			return nextLine;
			}
		else{
			return null;
			}
	
	}
	
	public void init() throws IOException, SQLException{
		
		for(File file:listOfFiles){
			
			parseNewFile(file.getAbsolutePath());
			
		}
		

	}
	
	public void displayLogs() throws SQLException {
		cm.queryall();
	}

	
	
	private void parseNewFile(String path) throws IOException, SQLException {
		
		
		System.out.println("Parsing file "+path);
		
		reader = new CSVReader(new FileReader(path),'\t');
		int counter = 0;
		
		String[] nextLine;
		
		while((nextLine = this.getNextLine())!=null){
			
			cm.insert(nextLine);
			System.out.println("Success"+" "+counter);
			if(counter==999){
				cm.executeBatch();
				counter = -1;
			}
			counter ++;
		}
		
		cm.executeBatch();
		//cm.queryall();//remove after debugging
		//cm.clockquery();
		
	}
	


}
