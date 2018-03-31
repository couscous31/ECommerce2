package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;

public class Panier implements Serializable{
	
	//Transfo assos Uml et Java avec ligne de commande
	private List<LigneCommande> listeLignecommande;

	//Getter et setter
	public List<LigneCommande> getListeLignecommande() {
		return listeLignecommande;
	}

	public void setListeLignecommande(List<LigneCommande> listeLignecommande) {
		this.listeLignecommande = listeLignecommande;
	}
	

}
