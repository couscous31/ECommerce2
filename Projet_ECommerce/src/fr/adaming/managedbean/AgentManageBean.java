package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Agent;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IAgentService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AgentManageBean implements Serializable {

	@EJB // Transformation UML en java
	IAgentService agentService;
	
	@EJB
	ICategorieService categorieService;
	
	@EJB
	IProduitService produitService;
	
	@EJB
	IClientService clientService;

	// Declarer l'agent comme attribut d'un Managebean
	private Agent agent;
	private List<Categorie> listeCetegorie;;
	private List<Produit> listeProduit;
	private Client client;
	

	// Constructeur
	public AgentManageBean() {
		this.agent = new Agent() ;
		this.client=new Client();
	}

	// G+S

	public List<Categorie> getListeCetegorie() {
		return listeCetegorie;
	}


	public void setListeCetegorie(List<Categorie> listeCetegorie) {
		this.listeCetegorie = listeCetegorie;
	}


	public List<Produit> getListeProduit() {
		return listeProduit;
	}


	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}


	public IAgentService getAgentService() {
		return agentService;
	}

	public void setAgentService(IAgentService agentService) {
		this.agentService = agentService;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

	// Methodes se connecter

	

	public String seConnecter() {

		Agent aOut = agentService.isExist(this.agent);
		//Client clOut= clientService.isExist(this.client);
		
		this.listeProduit=produitService.getAllProduit();   //, clOut
		//this.listeCetegorie=categorieService.consulatationCategorieService(aOut, clOut);

		if (aOut != null) {
			
			
			
			// Ajouter l'agent comme attribut de la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("agentListe", aOut);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitsListe", this.listeProduit);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("categorieListe", this.listeCetegorie);
			
			return "success";

		} else {
			return "echec";
		}
	}

	public String seDeconnecter() {

		// Fermer la session ouvert
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "accueil";
	}
}
