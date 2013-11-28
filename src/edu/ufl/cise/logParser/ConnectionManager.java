package edu.ufl.cise.logParser;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private String dbURL = "jdbc:mysql://localhost:3306/Dos4?";
	private String usernamepasswd = "user=shreyas&password=abcd";
	
	private int trueid = 0;
	
	ConnectionManager() throws SQLException{
		connect = DriverManager.getConnection(dbURL + usernamepasswd );
	}
	
	public void insert(String[] data) throws SQLException{
		preparedStatement = connect.prepareStatement("insert into  log values (?, ?, ?, ?, ? , ?, ?)");

		preparedStatement.setInt(1, trueid);
		trueid ++;
		preparedStatement.setInt(2, new Integer(data[0]));
		preparedStatement.setString(3, data[1]);
		preparedStatement.setString(4, data[2]);
		preparedStatement.setString(5, data[3]);
		preparedStatement.setString(6, data[4]);
		preparedStatement.setString(7, data[5]);
		
		preparedStatement.executeUpdate();
		
	}
	
	public void query() throws SQLException{
		
		resultSet = statement.executeQuery("select * from log");
		
		while (resultSet.next()) {
		
			int id = resultSet.getInt(1);
		    int clock = resultSet.getInt(2);
		    String timestamp = resultSet.getString(3);
		    String direction = resultSet.getString(4);
		    String sr = resultSet.getString(5);
		    String mesg = resultSet.getString(6);
		    String rs = resultSet.getString(7);

		    System.out.println(id+"\t"+clock+"\t"+timestamp+"\t"+direction+"\t"+sr+"\t"+mesg+"\t"+rs+"\n");

		    
		}
		
		
	}
	
	public void resetDatabase() throws SQLException{
		
		statement = connect.createStatement();
		statement.executeUpdate("delete from log");
	
	}
	
	public void test() throws SQLException{
		
		statement = connect.createStatement();
	    //resultSet = statement.executeQuery("insert into log values (1,2,3,4,5,6)");
	    statement.executeUpdate("insert into log values (1,2,3,4,5,6,7)");
	    resultSet = statement.executeQuery("select * from log");


	}


}
