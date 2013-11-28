package edu.ufl.cise.logParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import au.com.bytecode.opencsv.CSVReader;

public class CSVParser {
	
	CSVReader reader = null;
    
	CSVParser() throws FileNotFoundException{
		reader = new CSVReader(new FileReader("C:\\Users\\Shreyas\\Documents\\GitHub\\LogParser\\Supervisor"),'\t');
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
		
		String[] nextLine = this.getNextLine();
		
		cm.resetDatabase();
		cm.insert(nextLine);
		cm.query();
	}

}
