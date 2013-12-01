package edu.ufl.cise.logParser;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import au.com.bytecode.opencsv.CSVReader;

public class Application {

	/**
	 * @param args log path
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
		
		CSVParser reader = new CSVParser("C:\\Users\\Shreyas\\Documents\\GitHub\\LogParser\\logs");
		reader.init();
		reader.displayLogs();

	}

}
