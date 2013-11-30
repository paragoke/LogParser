package edu.ufl.cise.logParser;

import java.sql.SQLException;

public class QueryLog {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionManager cm = new ConnectionManager();
		cm.whereSenderOrReceiver("Supervisor");

	}

}
