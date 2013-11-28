package edu.ufl.cise.logParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import au.com.bytecode.opencsv.CSVReader;

public class CSVParser {
	
	CSVReader reader = null;
    
	CSVParser(String path) throws FileNotFoundException{
		reader = new CSVReader(new FileReader(path),'\t');
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
		
		ConnectionManager cm = new ConnectionManager();
		String[] nextLine = null;
		cm.resetDatabase();
		int counter = 0;
		
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
		cm.query();
	}

}
