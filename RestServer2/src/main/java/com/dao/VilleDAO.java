package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> findAllVilles();
	
	public ArrayList<Ville> findSpecVille(String codeCommune);
	
	public boolean addCity(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne, String latitude, String longitude);
	
	public boolean updateCity(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne, String latitude, String longitude);

	public boolean deleteCity(String codeCommune);
	
}
