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
	private PreparedStatement querypreparedStatement = null;
	private ResultSet resultSet = null;
	private ResultSet queryresultSet = null;
	
	private String dbURL = "jdbc:mysql://localhost:3306/Dos4?rewriteBatchedStatements=true";
	private String username = "shreyas";
	private String password = "abcd";
	
	private int trueid = 0;
	
	ConnectionManager() throws SQLException{
		connect = DriverManager.getConnection(dbURL,username,password );
		connect.setAutoCommit(false);
		preparedStatement = connect.prepareStatement("insert into  log values (?, ?, ?, ?, ? , ?, ?)");
		preparedStatement.clearBatch();
	}
	
	public void insert(String[] data) throws SQLException{

		preparedStatement.setInt(1, trueid);
		trueid ++;
		preparedStatement.setInt(2, new Integer(data[0]));
		preparedStatement.setString(3, data[1]);
		preparedStatement.setString(4, data[2]);
		preparedStatement.setString(5, data[3]);
		preparedStatement.setString(6, data[4]);
		preparedStatement.setString(7, data[5]);
		
		preparedStatement.addBatch();
		
	}
	
	public void executeBatch() throws SQLException {
		
		preparedStatement.executeBatch();
		connect.commit();
		
	}
	
	public void clockat(int time) throws SQLException{
		
		PreparedStatement clockatquery = connect.prepareStatement("select * from log where clock = ? order by clock asc");
		clockatquery.setInt(1, time);
		
		resultSet = clockatquery.executeQuery();
		
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

	public void clockbetween(int time1,int time2) throws SQLException{
		
		PreparedStatement clockbetweenquery = connect.prepareStatement("select * from log where clock between ? and ? order by clock asc");
		clockbetweenquery.setInt(1, time1);
		clockbetweenquery.setInt(2, time2);
		
		resultSet = clockbetweenquery.executeQuery();
		
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
	
	public void whereDirection(String givenDirection) throws SQLException{
		
		PreparedStatement directionquery = connect.prepareStatement("select * from log where direction = ? order by clock asc");
		directionquery.setString(1, givenDirection);
		
		resultSet = directionquery.executeQuery();
		
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
	
	public void logOfActor(String actor) throws SQLException{
		
		PreparedStatement actorquery = connect.prepareStatement("select * from log where actor = ? order by clock asc");
		actorquery.setString(1, actor);
		
		resultSet = actorquery.executeQuery();
		
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
	
	public void whereMessage(String msg) throws SQLException{
		
		PreparedStatement msgquery = connect.prepareStatement("select * from log where mesg = ? order by clock asc");
		msgquery.setString(1, msg);
		
		resultSet = msgquery.executeQuery();
		
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
	
	public void whereSenderOrReceiver(String actor) throws SQLException{
		
		PreparedStatement actorquery = connect.prepareStatement("select * from log where otheractor = ? order by clock asc");
		actorquery.setString(1, actor);
		
		resultSet = actorquery.executeQuery();
		
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
	
	
	public void queryall() throws SQLException{
		
		resultSet = statement.executeQuery("select * from log order by clock asc");
		
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
	
	/*public void clockquery() throws SQLException{
		
		querypreparedStatement = connect.prepareStatement("select * from log where clock=19000");
		queryresultSet = querypreparedStatement.executeQuery();
		
		while (queryresultSet.next()) {
			
			int id = queryresultSet.getInt(1);
		    int clock = queryresultSet.getInt(2);
		    String timestamp = queryresultSet.getString(3);
		    String direction = queryresultSet.getString(4);
		    String sr = queryresultSet.getString(5);
		    String mesg = queryresultSet.getString(6);
		    String rs = queryresultSet.getString(7);

		    System.out.println(id+"\t"+clock+"\t"+timestamp+"\t"+direction+"\t"+sr+"\t"+mesg+"\t"+rs+"\n");

		    
		}
		
	}*/
	
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
