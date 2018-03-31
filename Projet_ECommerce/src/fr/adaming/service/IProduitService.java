package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Local

public interface IProduitService {
	
	public List<Produit> getAllProduit(); 
	
	public Produit addProduit(Produit pr);
	
	public int deleteProduit(Produit pr);
	
	public int updateProduit(Produit pr);
	
	public Produit getProduitById(Produit pr);


}
