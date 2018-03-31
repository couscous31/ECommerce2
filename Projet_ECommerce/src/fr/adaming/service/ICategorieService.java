package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;

@Local
public interface ICategorieService {
	
	public List<Categorie> consulatationCategorieService ();   
	
	public Categorie ajouterCategorieService (Categorie cat);

	public int modifierCategorieService (Categorie cat);

	public int supprimerCategorie (Categorie cat);
	
	public  Categorie getCategorieByIdService(Categorie cat);




}
