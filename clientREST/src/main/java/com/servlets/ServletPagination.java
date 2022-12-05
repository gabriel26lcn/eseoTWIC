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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class ServletPagination extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = "http://localhost:8080/ville";
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);
		
		String rp = target.request()
								.accept(MediaType.APPLICATION_JSON)
								.get(String.class);
		
		Gson g = new Gson();
		Ville[] villes = g.fromJson(rp, Ville[].class);
		
	    request.setAttribute( "data", villes );
	    
	    int actualPage;
	    
	    if(request.getParameterMap().containsKey("actualPage")) {
			String action = request.getParameter("action");
			actualPage = Integer.parseInt(request.getParameter("actualPage"));
			if(action.equals("next")) {
				actualPage++;
			}else {
				actualPage--;
			}
	    }
	    else {
			actualPage = 1;
	    }
	    
	    request.setAttribute( "page", actualPage);
	    request.setAttribute( "data", villes );
		this.getServletContext().getRequestDispatcher( "/WEB-INF/pagination.jsp" ).forward( request, response );
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		System.out.println(longitude);*/
		
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
		

		doGet(request, response);
	}
	
}
