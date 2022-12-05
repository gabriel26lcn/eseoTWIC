package com.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;
import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.fasterxml.jackson.core.type.TypeReference;

public class ServletListe extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = "http://localhost:8080/ville";
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(uri);
		
		String rp = target.request()
								.accept(MediaType.APPLICATION_JSON)
								.get(String.class);
		
		//System.out.println(rp);
		
		Gson g = new Gson();
		Ville[] villes = g.fromJson(rp, Ville[].class);
		
	    request.setAttribute( "data", villes );
		
		//if(request.getParameter("ville1")!="null" && request.getParameter("ville2")!="null") 
	    
	    if(request.getParameterMap().containsKey("ville1") && request.getParameterMap().containsKey("ville2")){
		
			String ville1 = request.getParameter("ville1");
			String ville2 = request.getParameter("ville2");
			System.out.println(ville1);
			System.out.println(ville2);
			
			double lat1 = 0.0;
			double long1 = 0.0;
			
			double lat2 = 0.0;
			double long2 = 0.0;
			
			for(int i=0; i<villes.length; i++) {
				if(villes[i].getCodeCommuneInsee().equals(ville1)) {
					System.out.println("ville 1 found");
					lat1 = Double.parseDouble(villes[i].getLatitude());
					long1 = Double.parseDouble(villes[i].getLongitude());
				}
				if(villes[i].getCodeCommuneInsee().equals(ville2)) {
					System.out.println("ville 2 found");
					lat2 = Double.parseDouble(villes[i].getLatitude());
					long2 = Double.parseDouble(villes[i].getLongitude());
				}
			}
			
			System.out.println(lat1);
			System.out.println(long1);
			System.out.println(lat2);
			System.out.println(long2);
			
		    final int R = 6371; // Radius of the earth
	
		    double latDistance = Math.toRadians(lat2 - lat1);
		    double lonDistance = Math.toRadians(long2 - long1);
		    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
		            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R * c;
	
		    distance = Math.pow(distance, 2);
		    
		    System.out.println(distance);
			
		    request.setAttribute( "distance", distance );
	    }
		this.getServletContext().getRequestDispatcher( "/WEB-INF/liste.jsp" ).forward( request, response );
		
	}

}
