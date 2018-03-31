package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@Stateless

public class ProduitDaoImpl implements IProduitDao{
	
	@PersistenceContext(unitName="pu")
	private EntityManager em;

	
	//Get All Produit
	@Override
	public List<Produit> getAllProduit() { 
		
		//req jpql
		String req="SELECT pr FROM Produit AS pr ";   
		
		//création de query
		Query query=em.createQuery(req);
		
		//envoyer la req et récup résultat
		return query.getResultList();
	}
	
	
	//Add Produit
	@Override
	public Produit addProduit(Produit pr) {
		em.persist(pr);
		return pr;
	}

	
	//Delete Produit
	@Override
	public int deleteProduit(Produit pr) {
		
		//req jpql
		String req1="DELETE FROM produits AS pr WHERE pr.id=:pIdPr AND pr.agent.id=:pIdA";
		
		//création de query
		Query query1=em.createQuery(req1);
		
		//passage des params
		query1.setParameter("pIdPr", pr.getId());
		query1.setParameter("pIdA", pr.getAgent().getId());

		//envoyer la req et récup résultat
		return (int) query1.executeUpdate();
	}

	
	//Update Produit
	@Override
	public int updateProduit(Produit pr) {

		//req jpql
		String req2="UPDATE Produit SET pr.designation=:pDesi,"
									+ "pr.description=:pDesc,"
									+ "pr.prix=:pPrix,"
									+ "pr.quantite=:pQt,"
									+ "pr.photoProd=:pPhoto"
									+ " WHERE pr.id=:pIdPr AND pr.agent.id=:pIdAg";
		
		//création du query
		Query query2=em.createQuery(req2);
		
		//passages des params
		query2.setParameter("pDesi", pr.getDesignation());
		query2.setParameter("pDesc", pr.getDescription());
		query2.setParameter("pPrix", pr.getPrix());
		query2.setParameter("pQt", pr.getQuantite());
		query2.setParameter("pPhoto", pr.getPhotoProd());
		query2.setParameter("pIdPr", pr.getId());
		query2.setParameter("pIdAg", pr.getAgent().getId());
		
		//envoyer req et récup résultat
		return (int) query2.executeUpdate();
	}

	
	//Get Produit By Id
	@Override
	public Produit getProduitById(Produit pr) {

		//req jpql
		String req3="SELECT pr FROM Produit AS pr WHERE pr.id=:pIdPr AND pr.agent.id=:pIdAg";
		
		//création de query
		Query query3=em.createQuery(req3);
		
		//passage des params
		query3.setParameter("pIdPr", pr.getId());
		query3.setParameter("pIdAg", pr.getAgent().getId());
		
		//envoyer req et récup résultat
		return (Produit) query3.getSingleResult();
	}



}
