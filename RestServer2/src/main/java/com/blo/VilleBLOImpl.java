package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blo.VilleBLO;
import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {
	
	@Autowired
	private VilleDAO villeDAO;
	
	public ArrayList<Ville> getInfosVilles() {
		ArrayList<Ville> listeVille = new ArrayList<Ville> ();
		
		listeVille = villeDAO.findAllVilles();
		return listeVille;
	}
	
	public ArrayList<Ville> getInfosVilles(String codeCommune) {
		ArrayList<Ville> listeVille = new ArrayList<Ville> ();
		
		listeVille = villeDAO.findSpecVille(codeCommune);
		return listeVille;
	}

	
	public boolean addVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne, String latitude, String longitude) {

		System.out.println(nomCommune);
		return villeDAO.addCity(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
	
	}
	
	public boolean updateVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne, String latitude, String longitude) {
		
		return villeDAO.updateCity(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
	
	}
	
	public boolean deleteVille(String codeCommune) {
		
		return villeDAO.deleteCity(codeCommune);

	
	}

	
}
