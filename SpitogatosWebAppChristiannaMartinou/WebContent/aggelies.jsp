<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%
	String connectionURL = "jdbc:mysql://localhost:3306/realestate";
	Connection connection = null;
	Statement statement = null;	
	PreparedStatement prst = null;
	ResultSet rs = null;

%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type = "text/css">

	.userheader{
		text-align: center;
		font-weight: bold;
		float: top;		
	}
	.aggeliescontainer{			
		height:100vh;
		position:relative;			
	}
	.newAggelia{
		float:left;
		width:35%;		
		position:absolute;
	}
	.userfooter{
		text-align: center;
		font-style: italic;
		position: fixed;
		bottom:0px;
		height:5%;
		width:100%;		
	}	
	.entryAd{
		float:left;
		width:100%;		
	}
	.listOfAggelies{
		float:right;
		width:60%;
		height:70%;		
	}
	p#aggContent{
		text-align: left;
	}
	
	#aggHlist{
		text-decoration: underline;
	}
	#entryTitle{
		text-align: center;
	}
	
</style>

<script>

function confirm(){
	alert("Επιτυχής αφαίρεση της αγγελίας σας");
}
</script>


</head>
<body>
<% 
String username = request.getParameter("username"); 
String userid = request.getParameter("userid");
String alert = request.getParameter("alert");
String properties = request.getParameter("properties");

if(alert.equals("true")){
	
	%>
	<script>confirm();</script>
	<% 
	alert = "false";
}

%>
		<br/>
		<br/>
		<div class="aggeliescontainer">
			<div class="userheader"><p>Σύστημα διαχείρησης αγγελιών(Καλώς ήλθες  <%=username%>)<br><br></p></div>
			<div class="newAggelia">				
				<form method="get" action="entryServlet">
					<p id="entryTitle">Καταχώρηση νέας αγγελίας</p>
					<table class="entryAd">
						<tr>
							<td>Τιμή:</td>
							<td><input type="number" name="price" min=50 max=5000000 size=12 required/></td>
						</tr>
						<tr>
							<td>Περιοχή:</td>
							<td><select name="area" required>
									<option value="" selected disabled hidden>Επιλογή εδώ</option>
									<option value="Athens">Αθήνα</option>
									<option value="Thessaloniki">Θεσσαλονίκη</option>
									<option value="Patra">Πάτρα</option>
									<option value="Hrakleio">Ηράκλειο</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>Διαθεσιμότητα:</td>
							<td><select name="availability" required>
									<option value="" selected disabled hidden>Επιλογή εδώ</option>
									<option value="rent">ενοικίαση</option>
									<option value="sell">πώληση</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>Τετραγωνικά:</td>
							<td><input type="number" name="squareMeters" min=20 max =1000 size=18 required/></td>
						</tr>
						<tr>
							<td colspan=2><br><button name="submit" type="submit" value="submit">Υποβολή</button></td>
						</tr>
					</table>
					<input type="hidden" name="userid" value="<%=userid%>" />
					<input type="hidden" name="username" value="<%=username%>" />
					<input type="hidden" name="properties" value="<%=properties%>" />
				</form>
			</div>
		
			
<%
	Class.forName("com.mysql.cj.jdbc.Driver");
	connection = DriverManager.getConnection(connectionURL, "root", "");
	statement = connection.createStatement();			

	%>
			<div class="listOfAggelies">
				<p id="aggHlist">Λίστα αγγελιών</p>
				
				<table id="aggTable">
				<%
				String mysqlSelect = "SELECT * FROM "+ properties;       		   
			    prst = connection.prepareStatement(mysqlSelect);							
				rs=prst.executeQuery();
				
				int i = 0;
				while (rs.next()) {
				i++;
					%>
					<form method="get" action="DeleteServlet">
						<tr id="<%=i%>">
								<td><br><p id="aggContent"><%=rs.getString("area")%>, <%=rs.getString("availability")%>, <%=rs.getString("price")%> ευρώ, <%=rs.getString("squareMeters")%> τ.μ.</p><td>
								<td><br><button name="submit" type="submit" value="submit">Διαγραφή</button></td>
						</tr>
						<input type="hidden" name="id" value="<%=rs.getString("id")%>" />
						<input type="hidden" name="userid" value="<%=userid%>" />
						<input type="hidden" name="username" value="<%=username%>" />
						<input type="hidden" name="properties" value="<%=properties%>" />
					</form>
				<%
				}
				
				rs.close();
				connection.close();
				statement.close();
				%>
				</table>
			
			</div>
				
			<div class="userfooterpush"></div>	
		</div>	
			
			
			
		<div class="userfooter">All rights reserved</div>	
			
			
			
			
			
</body>
</html>