package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;

@Local
public interface ICategorieDao {
	
	public List<Categorie> consulatationCategorie ();
	
	public Categorie ajouterCategorie (Categorie cat);
	
	public int modifierCategorie (Categorie cat);
	
	public int supprimerCategorie (Categorie cat);
	
	public  Categorie getCategorieById(Categorie cat);

}
