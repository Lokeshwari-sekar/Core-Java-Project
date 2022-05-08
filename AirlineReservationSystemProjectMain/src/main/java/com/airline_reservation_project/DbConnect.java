/**
 * Data base connection class-> it makes a connections between the spring tool shoot and mysql
 *         
 *         1.Driver->That implements the java databases connectivity (JDBC) API
 *                   
 *         2.url->data base management system jdbc driver uses to connect to a database
 *         
 *         3.username-> user name of the database
 *         
 *         4.password->pass word for the databases
 * 
 * 		   5.forname()-> method is loading the driver dynamically loads a java class at runtime
 * 
 *        6.DriverManger->Is that class making connection to database by passing arguments as a url ,username and password.
 *
 */

package com.airline_reservation_project;


import java.sql.Connection;

import java.sql.DriverManager;


public class DbConnect 
{
	//driver
	static String driver="com.mysql.cj.jdbc.Driver";
	//here the databases name is AIRLINE_RESERRVATION_SYSTEM
	//port number is 3306
	
	static String url="jdbc:mysql://localhost:3306/airline_reservation_system";
	//my sql username
	static String username="root";
	//my sql password
	static String password="root";
	static Connection con;
	public static Connection getconnection()
	{
		try
		{
			//loading driver
			Class.forName(driver);
			//using this arguments building the connection betweeen java and sql
			con=DriverManager.getConnection(url, username, password);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
		
	}
	

}
