<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.servlets.Ville"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier Commune</title>
<style><%@include file="style.css"%></style>
</head>
<body>
	<h1 align="center">Editez les champs du formulaire :</h1>
	<%
	Ville[] ville = (Ville[]) request.getAttribute("data");
	String codeCommune = ville[0].getCodeCommuneInsee();
	String nomCommune = ville[0].getNomCommune();
	String codePostal = ville[0].getCodePostal();
	String libelle = ville[0].getLibelleAcheminement();
	String ligne = ville[0].getLigne();
	String latitude = ville[0].getLatitude();
	String longitude = ville[0].getLongitude();
	%>
	<form class="formEdit" action="http://localhost:8181/clientREST/pagination" method="post">
		<label>Code de la commune : </label>
		<input class="editInput" type="text" name="codeInsee" id="inputCodeInsee" value="<%=codeCommune%>" readonly><br>
		<label>Nom de la commune : </label>
		<input class="editInput" type="text" name="nomCommune" id="inputNomCommune" value="<%=nomCommune%>" readonly><br>
		<label>Code Postal : </label>
		<input class="editInput" type="text" name="codePostal" id="inputCodePostal" value="<%=codePostal%>" readonly><br>
		<label>Libellé acheminement : </label>
		<input class="editInput" type="text" name="libelle" id="inputLibelle" value="<%=libelle%>" readonly><br>
		<label>Ligne : </label>
		<input class="editInput" type="text" name="ligne" id="inputLigne" value="<%=ligne%>" ><br>
		<label>Latitude : </label>
		<input class="editInput" type="text" name="latitude" id="inputLatitude" value="<%=latitude%>" readonly><br>
		<label>Longitude : </label>
		<input class="editInput" type="text" name="longitude" id="inputLongitude" value="<%=longitude%>" readonly><br>
		<input type="submit" value="Valider" class="validate">
	</form>
</body>
</html>