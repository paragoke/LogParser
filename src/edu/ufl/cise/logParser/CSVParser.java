package edu.ufl.cise.logParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import au.com.bytecode.opencsv.CSVReader;

public class CSVParser {
	
	CSVReader reader = null;
	ConnectionManager cm = new ConnectionManager();
    
	CSVParser(String path) throws FileNotFoundException, SQLException{
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
		cm.query();  //remove after debugging
	}
	
	public void parseNewFile(String path) throws IOException, SQLException {
		
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
		cm.query();//remove after debugging
		
	}
	
	public void displayLogs() throws SQLException {
		cm.query();
	}

}
