package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {
	
	@Autowired
	VilleBLO villeBLOService;

	/* GET */
	
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody	
	public ArrayList<Ville> get() {
		ArrayList<Ville> listeVille;
		System.out.println("getAll");
		listeVille = villeBLOService.getInfosVilles();	

		return listeVille;
	}
	
	@RequestMapping(value = "/ville/{codeCommune}", method = RequestMethod.GET)
	@ResponseBody	
	public ArrayList<Ville> get(@PathVariable("codeCommune") String codeCommune) {
		ArrayList<Ville> listeVille;
		System.out.println("getSpecific");
		listeVille = villeBLOService.getInfosVilles(codeCommune);	
		
		return listeVille;
	}
	
	
	@PostMapping("/ville/newVille")
	public ResponseEntity<String> post(@RequestBody Ville ville){
		
		String codeCommune = ville.getCodeCommuneInsee();
		String nomCommune = ville.getNomCommune();
		String codePostal = ville.getCodePostal();
		String libelleAcheminement = ville.getLibelleAcheminement();
		String ligne = ville.getLigne();
		String latitude = ville.getLatitude();
		String longitude = ville.getLongitude();
		
		System.out.println("add City" + ville.getNomCommune());
		System.out.println("Longitude : " + ville.getLongitude());
		
		Boolean isAdded = villeBLOService.addVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
		
		if (!isAdded) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't add Ville");
        }else {
        	return ResponseEntity.status(HttpStatus.OK).body("Ville added");
        }
	}
	
	
	@PutMapping("/ville/{codeCommune}")
	@ResponseBody	
	public ResponseEntity<String> put(@PathVariable("codeCommune") String codeCommune, @RequestBody Ville ville) {
		System.out.println("update City" + codeCommune);

		String nomCommune = ville.getNomCommune();
		String codePostal = ville.getCodePostal();
		String libelleAcheminement = ville.getLibelleAcheminement();
		String ligne = ville.getLigne();
		String latitude = ville.getLatitude();
		String longitude = ville.getLongitude();

		Boolean isUpdated = villeBLOService.updateVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
		
		if (!isUpdated) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't update Ville");
        }else {
        	return ResponseEntity.status(HttpStatus.OK).body("Ville updated");
        }
		
	}
	
	@DeleteMapping("/ville/{codeCommune}")
	@ResponseBody	
	public ResponseEntity<String> delete(@PathVariable("codeCommune") String codeCommune) {
		System.out.println("delete City" + codeCommune);
		
		Boolean isRemoved = villeBLOService.deleteVille(codeCommune);
		
		if (!isRemoved) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't delete Ville");
        }else {
        	return ResponseEntity.status(HttpStatus.OK).body("Ville deleted");
        }
	}
		
}
