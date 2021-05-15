
//hide the status messages 
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 $("#alertSuccess").hide();
	 }
	 $("#alertError").hide();
});

//Save
$(document).on("click", "#Button", function(event){
	
	//clear status messages
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form Validation
	var status = validateUserForm(); 
	
	//If not valid
	if (status != true){
		$("#alertError").text(status);
	    $("#alertError").show();
		return;
	}
	
	//If valid
	var type=($("#ID").val()=="") ? "POST" : "PUT";
	$.ajax({
		
		url : "UserAPI",
		type : type,
		data : $("#formUser").serialize(),
		dataType : "text",
		complete : function(response, status){
			onUserSaveComplete(response.responseText, status);
		}
	});
	
	
	
	//Displaying a success message to and resting the form
	//$("#alertSuccess").text("Sumbitted successfully.");
	//$("#alertSuccess").show();

	//$("#formUser")[0].reset();
	
});

function onUserSaveComplete(response, status){
	
	if(status == "success"){
	var resultSet = JSON.parse(response);

	if(resultSet.status.trim() == "success"){

		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();

		$("#divUserGrid").html(resultSet.data);
	}else if (resultSet.status.trim() == "error") {

		$("#alertDanger").text(resultSet.data)
		$("#alertDanger").show();

	
	}else if(status == "error"){

		$("#alertDanger").text("Error while saving.");
		$("#alertDanger").show();

	}else{

		$("#alertDanger").text("Unknown error while saving.");
		$("#alertDanger").show();

	}
		$("#ID").val("");
		$("#formUser")[0].reset();
}

}

//Update
$(document).on("click", ".btnUpdate", function(event){
	
	 $("#ID").val($(this).data("ID"));
	 $("#Name").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#Address").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#Telephone").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#Email").val($(this).closest("tr").find('td:eq(4)').text()); 
	 $("#UserName").val($(this).closest("tr").find('td:eq(5)').text()); 
	 $("#Password").val($(this).closest("tr").find('td:eq(6)').text()); 
	
});

function validateUserForm(){
	
	//Name
	if ($("#Name").val().trim() == "")
	{
	return "Insert the User Name.";
	}
	
	//Address
	if ($("#Address").val().trim() == "")
	{
	return "Insert the Address.";
	}
	
	//Telephone
	if ($("#Telephone").val().trim() == "")
	{
	return "Insert the Telephone No.";
	}
	
	//Email
	if ($("#Email").val().trim() == "")
	{
	return "Insert the Email.";
	}
	
	//UserName
	if ($("#UserName").val().trim() == "")
	{
	return "Insert the UserName.";
	}
	
	//Password
	if ($("#Password").val().trim() == "")
	{
	return "Insert the Password.";
	}

	return true;
}


//Remove 
$(document).on("click", ".btnRemove", function(event){
	
	$.ajax({
	
			url : "UserAPI",
			type : "Delete",
			data : "ID="+$(this).data("ID"),
			dataType : "text",
			complete : function(response, status){
		
				onUserDeleteComplete(response.responseText, status);
		
		}
	});
});

function onUserDeleteComplete(response, status){
	
	if(status == "success"){
		var resultSet = JSON.parse(response);
		
		if(resultSet.status.trim() == "success"){
			$("#alertSuccess").text("Successfully deletd.");
			$("#alertSuccess").show();
			
			$("#divUserGrid").html(resultSet.data);
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown error while deleting.");
		$("#alertError").show();
	}
	
	$("#formItem")[0].reset();
}

		
		
