<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.servlets.Ville"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste Villes</title>
<style><%@include file="style.css"%></style>
</head>
<body>
	<h1 align="center">Liste des villes</h1>
	<table class="tableau-style">
		<thead>
			<tr>
				<th>Nom Commune</th>
				<th>Code INSEE</th>
				<th>Code Postal</th>
				<th>Libelle Acheminement</th>
				<th>Ligne</th>
				<th>Latitude</th>
				<th>Longitude</th>
			</tr>
		</thead>
		<tbody id="tableauVilles" >
		</tbody>
	</table>
	<form action="" class="formPagination">
		<input class="change" type="submit" name="action" value="previous"/>
		<input class="page" type="text" name="actualPage" id="noPage"/>
		<input class="change" type="submit" name="action" value="next"/>
	</form>
	<%
	  Ville[] villes = (Ville[]) request.getAttribute("data");
	  int noPage = (int) request.getAttribute("page");
      for(int i=50*(noPage-1)+1; i<=50*noPage; i++){
	  	  String nomCommune = villes[i].getNomCommune();
	  	  String code = villes[i].getCodeCommuneInsee();
	  	  String cp = villes[i].getCodePostal();
	  	  String libelle = villes[i].getLibelleAcheminement();
	  	  String ligne = villes[i].getLigne();
	  	  String latitude = villes[i].getLatitude();
	  	  String longitude = villes[i].getLongitude();%>
	  	  <script>
	  	  	var input = document.getElementById("noPage");
			var p = "<%=noPage%>";
			input.value = p;
	  	  
	  	  	var name = "<%=nomCommune%>";
	  	  	var id = "<%=code%>";
	  	  	var pc = "<%=cp%>";
	  	  	var lib = "<%=libelle%>";
	  	  	var line = "<%=ligne%>";
	  	  	var lat = "<%=latitude%>";
	  	  	var lon = "<%=longitude%>";
	  	  	
	  	  	var tab = document.getElementById("tableauVilles");
	
	  	  	var row = tab.insertRow(-1);
	  	  	row.setAttribute("id", id);
	  	  	row.setAttribute("onclick", "location.href='http://localhost:8181/clientREST/edit?ville='+this.id");
	  	  	
	  	  	var cellName = row.insertCell(0);
	  	  	var cellcode = row.insertCell(1);
	  		var cellCp = row.insertCell(2);
	  		var cellLibelle = row.insertCell(3);
	  		var cellLigne = row.insertCell(4);
	  		var cellLatitude = row.insertCell(5);
	  		var cellLongitude = row.insertCell(6);
	  	  	
	  	  	var nameText = document.createTextNode(name);
	  	  	var idText = document.createTextNode(id);
	  		var pcText = document.createTextNode(pc);
	  		var libText = document.createTextNode(lib);
	  		var lineText = document.createTextNode(line);
	  		var latText = document.createTextNode(lat);
	  		var lonText = document.createTextNode(lon);
	  	  	
	  	  	cellName.appendChild(nameText);
	  	  	cellcode.appendChild(idText);
	  	  	cellCp.appendChild(pcText);
	  	  	cellLibelle.appendChild(libText);
	  	  	cellLigne.appendChild(lineText);
	  	  	cellLatitude.appendChild(latText);
	  	  	cellLongitude.appendChild(lonText);
	  	  </script>
	<%}%>
</body>
<footer>
	<a href="http://localhost:8181/clientREST/liste">--> Go to List Page</a>
</footer>
</html>