<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.servlets.Ville"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Villes List</title>
<style><%@include file="style.css"%></style>
</head>
<body>
	<h1 align="center">Choisissez deux villes :</h1>
	<form action="" class="formListes">
		<select name="ville1" id="ville1-select">
			<option value="">--Choisissez une première ville :</option>
		</select>
		<select name="ville2" id="ville2-select">
			<option value="">--Choisissez une seconde ville :</option>
		</select>
		<br>
		<input class="calculate" type="submit" value="Calculer" >
	</form>
	<input class="dist" type="text" id="dist">
	<% 
      Ville[] attribut = (Ville[]) request.getAttribute("data");
      for(Ville v:attribut){
    	  String nomCommune = v.getNomCommune();
    	  String code = v.getCodeCommuneInsee();%>
    	  <script>
    	  	var nom = "<%=nomCommune%>";
    	  	var id = "<%=code%>";
    	  	var opt1 = document.createElement("option");
    	  	opt1.value = id;
    	  	opt1.text = nom;
    	  	var opt2 = document.createElement("option");
    	  	opt2.value = id;
    	  	opt2.text = nom;
    	  	var select1 = document.getElementById("ville1-select");
    	  	select1.options.add(opt1, null);
    	  	var select2 = document.getElementById("ville2-select");
    	  	select2.options.add(opt2, null);
    	  </script>
      <%}
      double distance = (double) request.getAttribute("distance");
      if (distance != 0.0){%>
    	  <script>
    	  var dis = "<%=distance%>";
    	  var div = document.getElementById("dist");
    	  div.value = dis + " Kms";
    	  </script>   	  
      <%}
    %>
</body>
<footer>
	<a href="http://localhost:8181/clientREST/pagination">--> Go to Pagination Page</a>
</footer>
</html>