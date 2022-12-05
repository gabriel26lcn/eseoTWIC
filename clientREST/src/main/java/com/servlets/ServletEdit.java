package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

import com.google.gson.Gson;

public class ServletEdit extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("ville");
		
		String uri = "http://localhost:8080/ville/" + code;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);
		
		String rp = target.request()
								.accept(MediaType.APPLICATION_JSON)
								.get(String.class);
		
		System.out.println(rp);
		
		Gson g = new Gson();
		Ville[] ville = g.fromJson(rp, Ville[].class);
		
		/*System.out.println(uri);
		System.out.println(ville[0].getNomCommune());*/
		request.setAttribute( "data", ville );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/edit.jsp" ).forward( request, response );
		
	}
	
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codeCommune = request.getParameter("codeInsee");
		String nomCommune = request.getParameter("nomCommune");
		String codePostal = request.getParameter("codePostal");
		String libelle = request.getParameter("libelle");
		String ligne = request.getParameter("ligne");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		/*System.out.println(codeCommune);
		System.out.println(nomCommune);
		System.out.println(codePostal);
		System.out.println(libelle);
		System.out.println(ligne);
		System.out.println(latitude);
		System.out.println(longitude);
		
		String uri = "http://localhost:8080/ville/" + codeCommune;
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);
		
		JSONObject editVille = new JSONObject();
		editVille.put("codeInsee", codeCommune);
		editVille.put("nomCommune", nomCommune);
		editVille.put("codePostal", codePostal);
		editVille.put("libelleAcheminement", libelle);
		editVille.put("ligne", ligne);
		editVille.put("latitude", latitude);
		editVille.put("longitude", longitude);
		
		System.out.println(editVille);
		
		Response rp = target.request()
				.accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(editVille.toString(), MediaType.APPLICATION_JSON));
		

		//this.getServletContext().getRequestDispatcher( "/WEB-INF/edit.jsp" ).forward( request, response );

	}*/
	
}
