package fr.adaming.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;

@Stateless
public class AgentDaoImpl implements IAgentDao {

	//Pour injecter l'entité manager de em
	@PersistenceContext(unitName="pu") 
	private EntityManager em;
	
	@Override
	public Agent isExist(Agent a) {
		
		String req="SELECT ag FROM Agent as ag WHERE ag.mail=:pMail AND ag.mdp=:pMdp";
		
		Query query=em.createQuery(req);
		
		//Passage des params
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());
		
		return (Agent) query.getSingleResult();
	}
	
	

}
