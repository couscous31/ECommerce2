package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import fr.adaming.model.Agent;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;

@Stateless
public class CategorieDao implements ICategorieDao {
	@PersistenceContext(unitName="pu")
	private EntityManager em;

	@Override
	public List<Categorie> consulatationCategorie( ) { 
		//creation de la requete SQl:
		String req="select cat from Categorie as cat ";   
		
		//création d'un query :
		Query query=em.createQuery(req);
		
		//envoyer la requete et recuperer le resultat  :		
		return query.getResultList();
	}

	@Override
	public Categorie ajouterCategorie(Categorie cat) {
		//ajouter dans la table :
		em.persist(cat);
		
	
		
		
		return cat;
	}

	@Override
	public int modifierCategorie(Categorie cat) {
         //creation de la requete :
		String req="update Categorie cat set cat.nomCategorie=:pNom, cat.photoCat=:pPhoto , cat.description=pDescription where cat.idCategorie=:pIdCat and cat.agent.id=:pIdAg ";
		
		//creation d'un query :
		Query query=em.createQuery(req);

		//passages des parametres :
		query.setParameter("pNom",  cat.getNomCategorie() );
		query.setParameter("pPhoto", cat.getPhotoCat());
		query.setParameter("pDescription", cat.getDescription());
		query.setParameter("pIdCat", cat.getIdCategorie());
		query.setParameter("pIdAg", cat.getAgent().getId());
		
		//envoyer la requete et récupérer le résultat
		int verif= query.executeUpdate();
		
		return verif;
	}

	@Override
	public int supprimerCategorie(Categorie cat) {
		// creation de la requete :
		String req="delete from Categorie where cat.idCategorie=:pIdCat and cat.agent.id=:pIdAg ";
		
		//creation du query :
		Query query = em.createQuery(req);
		
		//passage des parametres :
		query.setParameter("pIdCat", cat.getIdCategorie());
		query.setParameter("pIdAg",cat.getAgent().getId());
		
		//envoyer la requete et recuperer le resultat :
		int verif=query.executeUpdate();
		
		return verif;
	}

	@Override
	public Categorie getCategorieById(Categorie cat) {

		//appel de la requete :
		String req = "select cat from Categorie where cat.idCategorie=:pId and cat.agent.id=:pIdAg";
		
		//creation du query :
		Query query = em.createQuery(req);
		
		//passages des parametres :
		query.setParameter("pIdCat", cat.getIdCategorie());
		query.setParameter("pIdAg", cat.getAgent().getId());
		
		
		return (Categorie) query.getSingleResult() ;
		
	}

}
