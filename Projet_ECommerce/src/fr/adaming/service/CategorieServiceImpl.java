package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Agent;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;


@Stateful
public class CategorieServiceImpl implements ICategorieService {
	
	@EJB
	private ICategorieDao categorieDao;

	@Override
	public List<Categorie> consulatationCategorieService() { 
		return categorieDao.consulatationCategorie(); 
	}

	@Override
	public Categorie ajouterCategorieService(Categorie cat) {
		return categorieDao.ajouterCategorie(cat);
	}

	@Override
	public int modifierCategorieService(Categorie cat) {
		return categorieDao.modifierCategorie(cat);
	}

	@Override
	public int supprimerCategorie(Categorie cat) {
		return categorieDao.supprimerCategorie(cat);
	}

	@Override
	public Categorie getCategorieByIdService(Categorie cat) {
		return categorieDao.getCategorieById(cat);
	}

}
