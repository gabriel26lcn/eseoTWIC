package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dao.VilleDAO;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	
	Connection connection;
    String url = "jdbc:mysql://localhost:3306/villes";
    String user = "root";
    String password = "password";
	
	public ArrayList<Ville> findAllVilles() {
		
		System.out.println("findAllVilles");
		
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		String listAllCity = "SELECT * FROM ville_france ";
		
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(url, user, password);
			if(connection.isValid(0)) {
				System.out.println("Connection created");
			}
			
			PreparedStatement prepState = connection.prepareStatement(listAllCity);
			ResultSet allCity = prepState.executeQuery();
			
			while(allCity.next()) {
				Ville ville = new Ville();
				ville.setCodeCommuneInsee(allCity.getString("Code_commune_INSEE"));
				ville.setNomCommune(allCity.getString("Nom_commune"));
				ville.setCodePostal(allCity.getString("Code_postal"));
				ville.setLibelleAcheminement(allCity.getString("Libelle_acheminement"));
				ville.setLigne(allCity.getString("Ligne_5"));
				ville.setLatitude(allCity.getString("Latitude"));
				ville.setLongitude(allCity.getString("Longitude"));
				
				listVille.add(ville);
			}
			
			connection.close();
			if(connection.isClosed()) {
				System.out.println("Connection closed");
			}
			
		} catch (ClassNotFoundException e) {
			throw new Error("Erreur ", e);
		} catch (SQLException e) {
            System.out.println(e.toString());  
		}
				
		return listVille;
	}
	
	public ArrayList<Ville> findSpecVille(String codeCommune) {
		
		System.out.println("findVille - Code :" + codeCommune);
		
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		String getCityWithId = 
							"SELECT * FROM ville_france "
							+ "WHERE ville_france.Code_commune_INSEE='" + codeCommune + "'";;
		
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(url, user, password);
			if(connection.isValid(0)) {
				System.out.println("Connection created");
			}
			
			Statement state = connection.createStatement();
			ResultSet cityWithId = state.executeQuery(getCityWithId);
			
			while(cityWithId.next()) {
				Ville ville = new Ville();
				ville.setCodeCommuneInsee(cityWithId.getString("Code_commune_INSEE"));
				ville.setNomCommune(cityWithId.getString("Nom_commune"));
				ville.setCodePostal(cityWithId.getString("Code_postal"));
				ville.setLibelleAcheminement(cityWithId.getString("Libelle_acheminement"));
				ville.setLigne(cityWithId.getString("Ligne_5"));
				ville.setLatitude(cityWithId.getString("Latitude"));
				ville.setLongitude(cityWithId.getString("Longitude"));
				
				listVille.add(ville);
			}
			
			connection.close();
			if(connection.isClosed()) {
				System.out.println("Connection closed");
			}
			
		} catch (ClassNotFoundException e) {
			throw new Error("Erreur ", e);
		} catch (SQLException e) {
            System.out.println(e.toString());
		}
				
		return listVille;
	}
	
	//Pourrait aussi être spécifique avec le code postal
	
	
	public boolean addCity(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne, String latitude, String longitude) {
		
		System.out.println("new ville : " + nomCommune);
		
		String addNewCity = "INSERT INTO ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES ('" + codeCommune + "', '" + nomCommune + "', '" + codePostal + "', '" + libelleAcheminement + "', '" + ligne + "', '" + latitude + "', '" + longitude + "');";
		
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(url, user, password);
			if(connection.isValid(0)) {
				System.out.println("Connection created");
			}
			
			Statement state = connection.createStatement();
			int addResult = state.executeUpdate(addNewCity);
			
		    if (addResult == 0) {
		    	System.out.println("Insertion failed");
		    	return false;
		    }
			
			connection.close();
			if(connection.isClosed()) {
				System.out.println("Connection closed");
			}
			
		} catch (ClassNotFoundException e) {
			throw new Error("Erreur ", e);
		} catch (SQLException e) {
            System.out.println(e.toString());
			return false;
		}
		return true;
	}

	public boolean updateCity(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement,
			String ligne, String latitude, String longitude) {
		
		System.out.println("UpdateVille : " + codeCommune);
		
		String updateCity = 
			      "UPDATE ville_france SET"
			      + " Nom_commune = '" + nomCommune + "'," + " Code_postal = '" + codePostal + "'," + " Libelle_acheminement = '" + libelleAcheminement + "'," +
			      " Ligne_5 = '" + ligne + "'," + " Latitude = '" + latitude + "'," + " Longitude = '" + longitude + "'" + 
			      "WHERE Code_commune_INSEE = '" + codeCommune + "';";

		//Revoir requête update
		
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(url, user, password);
			if(connection.isValid(0)) {
				System.out.println("Connection created");
			}
			
			Statement state = connection.createStatement();
			int updateResult = state.executeUpdate(updateCity);
			
		    if (updateResult == 0) {
		    	System.out.println("update failed");
		    	return false;
		    }
			
			connection.close();
			if(connection.isClosed()) {
				System.out.println("Connection closed");
			}
			
		} catch (ClassNotFoundException e) {
			throw new Error("Erreur ", e);
		} catch (SQLException e) {
            System.out.println(e.toString());
            return false;
		}
		return true;
	}

	public boolean deleteCity(String codeCommune) {

		System.out.println("DeleteVille : " + codeCommune);
		
		String deleteCity = 
			      "DELETE FROM ville_france WHERE Code_commune_INSEE = '" + codeCommune + "';";
		
		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(url, user, password);
			if(connection.isValid(0)) {
				System.out.println("Connection created");
			}
			
			Statement state = connection.createStatement();
			int deleteResult = state.executeUpdate(deleteCity);
			
		    if (deleteResult == 0) {
		    	System.out.println("update failed");
		    	return false;
		    }
			
			connection.close();
			if(connection.isClosed()) {
				System.out.println("Connection closed");
			}
			
		} catch (ClassNotFoundException e) {
			throw new Error("Erreur ", e);
		} catch (SQLException e) {
            System.out.println(e.toString());
            return false;
		}
		return true;
	}
	
}