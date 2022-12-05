package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {
	public ArrayList<Ville> getInfosVilles();
	public ArrayList<Ville> getInfosVilles(String codeCommune);
	public boolean addVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne, String latitude, String longitude);
	public boolean updateVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne, String latitude, String longitude);
	public boolean deleteVille(String codeCommune);
}
