<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import = "com.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body>
		<!-- start the card  -->
		<div class="container">
			<div class="row">
				<div class="col-8">
					
					<h1>User Management V10.1</h1>
					
					<form id="formUser" name="formUser"> 
					
					<!-- NAME -->
							Name:
							<input id="Name" name="Name" type="text"
 								class="form-control form-control-sm">
					
					<!-- Address -->
						<br> Address:
							<input id="Address" name="Address" type="text"
 								class="form-control form-control-sm">
						
					<!-- Telephone -->
						<br> Telephone:
							<input id="Telephone" name="Telephone" type="text"
 								class="form-control form-control-sm">
						
					<!-- Email -->
						<br> Email:
							<input id="Email" name="Email" type="text"
 								class="form-control form-control-sm">
						
					<!-- UserName -->
						<br> UserName:
							<input id="UserName" name="UserName" type="text"
 								class="form-control form-control-sm">
						
					<!-- Password -->
						<br> Password:
							<input id="Password" name="Password" type="text"
 								class="form-control form-control-sm">
 					
 						<div id="alertSuccess" class="alert alert-success"></div>	
						<div id="alertError" class="alert alert-danger"></div>
 				
 					<!-- The Save Button -->
					<br>
						<input id="Button" name="Button" type="button" value="Save"
 								class="btn btn-primary">
 					
 					<!-- The ID -->			
 						<input type="hidden" id="ID" name="ID" value="">						

 					</form>
					
					<br>
					<div id= "divUserGrid">
							<%
								User userRead = new User();
								out.print(userRead.readUser());
							%>
					</div>
						
			</div>
				</div>
					
		</div>
		
</body>
</html>


