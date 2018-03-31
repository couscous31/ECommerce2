package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;

@Stateless
public class ClientDaoImpl implements IClientDao {

	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	@Override
	public List<Client> getAllListClient(Client cl) {

		String req = "SELECT cl FROM Client";

		Query query = em.createQuery(req);

		return (List<Client>) query.getResultList();
	}

	@Override
	public Client addClient(Client cl) {

		em.persist(cl);

		return cl;
	}

	@Override
	public int update(Client cl) {

		// Requete
		String req = "UPDATE Clients cl SET cl.nom=:pNom, cl.adresse=:pAdresse, cl.email=:Email, cl.tel=:pTel, cl.mdp=:pMdp WHERE cl.id=:pId";
		// Query
		Query query = em.createQuery(req);
		// Passage des parametres
		query.setParameter("pNom", cl.getNomClient());
		query.setParameter("pAdresse", cl.getAdresse());
		query.setParameter("pEmail", cl.getEmail());
		query.setParameter("pTel", cl.getIdClient());
		query.setParameter("pMdp", cl.getMdp());

		int verif = query.executeUpdate();

		return verif;
	}

	@Override
	public int delete(Client cl) {

		// Requete
		String req = "DELETE FROM Clients cl WHERE cl.id=:pId";
		// Query
		Query query = em.createQuery(req);

		// Passage des parametre
		query.setParameter("pId", cl.getIdClient());

		int verif = query.executeUpdate();

		return verif;

	}

	@Override
	public Client isExist(Client cl) {

		String req = "SELECT cl FROM Client as cl WHERE cl.email=:pMail AND cl.mdp=:pMdp";

		Query query1 = em.createQuery(req);

		// Passage des params
		query1.setParameter("pMail", cl.getEmail());
		query1.setParameter("pMdp", cl.getMdp());

		return (Client) query1.getSingleResult();
	}

}
