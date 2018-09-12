<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
/* #first {border-style: outset;} */
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.min.js"></script>


</head>

<script type="text/javascript">
var key=null;
//alert("Hello");

window.favorite = [];
	$(document).ready(function() {
		var name = null;
		
		$(".btn").click(function() {
	
		var xx=$(this).attr('name');
	
		 localStorage.setItem("option", xx);
		 $("#showform").show(function(){
			var aa = localStorage.getItem("option");
		
			key = aa;
		$("."+xx).hide();
		 	
		});
	
		 $("#btn1").click(function() {
			var transitionsArray = [];
			
				
			$.each($(".transition option:selected"), function() {
			
				transitionsArray.push($(this).val());
			});
			
			var value =  transitionsArray.join("#");
			
		
	
			 
			var asd = key +":" +value;
			 window.favorite.push(asd);
			
			var json = JSON.stringify(favorite);
	//		alert(json);
			if(sessionStorage.getItem("asd") === null)
				{
				alert("in if")
				sessionStorage.setItem("asd", json);
				//set remaining
					
				}
			else
				{
			//	alert("in else")
				var json1 = sessionStorage.getItem("asd");
				var json2 = json1 +","+json;
				sessionStorage.setItem("asd", json2);
				alert(json2);
				}
			window.location.reload();
			
			
		});// selected values
		 
		});
		$("#showform").hide();
		
	});	
	function openclick() {
		$("#open").click(function(event) {
			var button = event.target;
			var sss = button.id;
		});
		

	}
	 function data()
	 {
		  var hiddenSelectedVehicles = document.getElementById("selectedVehicles");
		  hiddenSelectedVehicles.value =sessionStorage.getItem("asd");
		  var form = document.getElementById("myFormId");
	        form.submit();
	 }

	//openclick()
</script>
<body style="background-color:">


	<dir class="jumbotron">
		<h1 style="text-align: center">Flowable</h1>
	</dir>
	
	<form action="http://localhost:8080/UIflowable/status" method="post" id="myFormId">
	  
	
	
	
	<div class="col-md-4">
		<dir class="jumbotron">
			<div id="first">
				<!-- <center><h1><a href="#" class="">Some text</a> -->
				<button type="button" id="open" name="Open" class="btn">Open</button>
				</br> </br>
				<button type="button" id="Inprogress" name="Inprogress" class="btn">Inprogress</button>
				</br> </br>
				<button type="button" id="Approve" name="Approve" class="btn">Approve</button>
				</br> </br>
				<button type="button" id="Review" name="Review" class="btn">Review</button>
				</br> </br>
				<button type="button" id="Done" name="Done" class="btn">Done</button>
				</h1>
				</center>
			</div>
		</dir>

	</div>
	<!--  first div  -->

<span id="asd">
	<div id="div2" class="col-md-4">
		<dir class="jumbotron">
			<div>
				<center></center>

				<p class="outset"></p>
			</div>
			<p>Press control key (Ctrl) to select multiple values:</p>
			<div id="showform">
				<!-- <form id="frm"> -->
					<label>Transition:</label> <select class="transition" multiple="multiple"
						size="5">
						<option class="Open">Open</option>
						<option class="Inprogress">Inprogress</option>
						<option class="Approve">Approve</option>
						<option class="Review">Review</option>
						<option class="Done">Done</option>
					</select>
					<button type="button" id="btn1">Get Values</button>
				<!-- </form> -->
			</div>
		</dir>
	</div>
	<!-- second div -->


	<div class="col-md-4">
		<div>
			<center>
				<h1>Test UI WorkFlow</h1>
			</center>
			
		</div>

	</div><!-- Third div -->
</span>
<input type="hidden" name="selectedVehicles" id="selectedVehicles"/>
<input  type = "button" value = "Submit" onclick="data()" />

</form>

</body>
</html>