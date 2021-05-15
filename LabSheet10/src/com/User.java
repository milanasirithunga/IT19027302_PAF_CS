package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	//A common method to connect to the Databse
	private Connection Connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//provide the correct details: DBServer/DBName, UserName, Password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	public String readUser() {
		
		String output = "";
		try {
			Connection con = Connect();
			if(con == null) {
				return "Error while connecting to the database for reading";
			}
			
			// Prepare the HTML table to be displayed
			/*output = "<table border='1'><tr><th>ID</th><th>Name</th><th>Address</th>"
					+ "<th>Telephone</th><th>Email</th>"
					+ "<th>UserName</th><th>Password</th>"
					+"<th>Update</th><th>Remove</th></tr>";*/
			output = "<div class='table-wrapper-scroll-y my-custom-scrollbar'><table border='1' class='table'><thead class='thead-dark'><tr><th>ID</th><th>Name</th><th>Address</th>" +
					"<th>Telephone</th>" +  
					"<th>Email</th>" +
					"<th>UserName</th>" +
					"<th>Password</th>" +
					"<th>Update</th><th>Remove</th></tr></thead>";
			
			String query= "select* from userservice";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// Iterate through the rows in the result set
			while(rs.next()) {
			
			String ID = Integer.toString(rs.getInt("ID"));
			String Name = rs.getString("Name");
			String Address = rs.getString("Address");
			String Telephone = rs.getString("Telephone");
			String Email = rs.getString("Email");
			String UserName = rs.getString("UserName");
			String Password = rs.getString("Password");
			
			// Add into the HTML table
			
			output += "<tr><td>"+ID+"</td>";
			output += "<td>"+Name+"</td>";
			output += "<td>"+Address+"</td>";
			output += "<td>"+Telephone+"</td>";
			output += "<td>"+Email+"</td>";
			output += "<td>"+UserName+"</td>";
			output += "<td>"+Password+"</td>";
			
			// Buttons
			output += "<td>"
							+"<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+ID+"'>"
							+"</td>"+ 
							"<td>"+ 
							"<input name='btnRemove' type='button' value='Remove'  class='btnRemove btn btn-danger' data-itemid='"+ID+"'>"+ 
							"</td></tr>";
					
					
					//"<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-ID='" + ID + "'></td>"
					//+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-ID='" + ID + "'>" + "</td></tr>";
			
			}
			con.close();
			
			// Complete the HTML table
						output += "</table>";
							
		}catch(Exception e) {
			
			output = "Error while reading ";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
    public String insertUser(String Name, String Address, String Telephone, String Email, String UserName,String Password) {
		
		String output = "" ;
		
		try {
			Connection con = Connect();
			if(con == null) {
				return "Error while connecting to the database for inserting. "; 
			}
			
			//create a prepard statement 
			String query = "insert into userservice (Name,Address,Telephone,Email,UserName,Password) values(?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			// binding values
			pst.setString(1,Name);
			pst.setString(2,Address);
			pst.setString(3,Telephone);
			pst.setString(4,Email);
			pst.setString(5,UserName);
			pst.setString(6,Password);
			
			// execute the statement
			pst.executeUpdate();
			con.setAutoCommit(false);
			con.close();
			output = "Inserted successfully";
			
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
		}catch(Exception e) {
			//output = "Error while inserting ";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting.\"}";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
    
    public String UpdateUser(String ID,String Name, String Address, String Telephone, String Email, String UserName,String Password) {
    	String output = "";
		Connection con = Connect();
		try{
		
			if(con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "update userservice set Name=?, Address=?, Telephone=?, Email=?, UserName=?, Password=?"
					+ " where id=?";
			
			PreparedStatement pddstmt = con.prepareStatement(query);
			
			pddstmt.setString(1,Name);
			pddstmt.setString(2,Address);
			pddstmt.setString(3,Telephone);
			pddstmt.setString(4,Email);
			pddstmt.setString(5,UserName);
			pddstmt.setString(6,Password);
			pddstmt.setInt(7,Integer.parseInt(ID));
			
			
			// execute the statement
			pddstmt.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
			output = "Updated successfully.";
			
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
    	
    }catch (Exception e) {
    	
    	//output = "Error while updating.";
    	output = "{\"status\":\"error\", \"data\":\"Error while updating.\"}";
		System.err.print(e.getMessage());
    }
    	    
	return output;
	
 }
    public String deleteUser(String ID) {
    	String output = "";
    	try {
    		
    		Connection con = Connect();
    		if(con==null) {
				return "Error while connecting to database for deleting.";
				
		}
    		// create a prepared statement
    		String query = "delete from userservice where ID=?";
			PreparedStatement pddstmt = con.prepareStatement(query);
			
			//binding values
			pddstmt.setInt(1, Integer.parseInt(ID));
			
			// execute the statement
			pddstmt.execute();
			 con.close();
			
			String newUser = readUser();
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
			
    	}catch(Exception e) {
    		
    		//output = "Error while deleting the userservice.";
    		output = "{\"status\":\"error\", \"data\":\"Error while deleting the userservice.\"}";
			System.err.print(e.getMessage());
    	}
    	return output;
    }
    
	

}
