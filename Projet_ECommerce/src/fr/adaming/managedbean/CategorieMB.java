package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import fr.adaming.model.Agent;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieMB implements Serializable {

	// transformer association uml en java :
	@EJB
	ICategorieService categorieService;

	// déclaration des attributs envoyées à la page :
	private Categorie categorie;
	private Agent agent;

	private boolean indice;

	HttpSession catSession;

	// constructeur vide :
	public CategorieMB() {
		this.categorie = new Categorie();
		this.indice = false;
	}

	@PostConstruct
	public void init() {
		// récupérer la session ouverte
		catSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		// récupérer l'agent stocké dans la session :
		this.agent = (Agent) catSession.getAttribute("agentListe");

	}

	// get et set
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}

	// méthodes metier

	// ajouter une categorie :
	public String ajouterCategorie() {
		// appel de la methode
		Categorie catOut = categorieService.ajouterCategorieService(categorie);

		if (catOut.getIdCategorie() != 0) {
			// recuperer la nouvelle liste de categorie :
			List<Categorie> liste = categorieService.consulatationCategorieService();  

			// mettre à jour la session :
			catSession.setAttribute("categorieListe", liste); 

			return "accueilAgent";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout categorie : fail !!!"));
			return "ajouterCategorie";
		}

	}

	// modifier categorie :
	public String modifierCategorie() {
		// appel de la methode :
		int verif = categorieService.modifierCategorieService(categorie);

		if (verif != 0) {
			// récuperer la nouvelle liste de categories :
			List<Categorie> liste = categorieService.consulatationCategorieService(); 

			// mettre à jour la session :
			catSession.setAttribute("categorieListe", liste);

			return "accueilAgent";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modif categorie : fail !!!"));
			return "modifierCategorie";
		}
	}

	// supprimer categories :
	public String supprimerCategorie() {

		int verif = categorieService.supprimerCategorie(categorie);

		if (verif != 0) {
			// recuperer la nouvelle liste :
			List<Categorie> liste = categorieService.consulatationCategorieService();   

			// mettre à jour la session :
			catSession.setAttribute("categorieListe", liste);

			return "accueilAgent";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression categorie : fail !!!"));

			return "supprimerCategorie";
		}
	}

	// rechercher une categorie par son id :
	public String rechercherCategorieById() {
		try {

			Categorie catOut = categorieService.getCategorieByIdService(categorie);

			this.categorie = catOut;
			this.indice = true;

			return "rechercherCategorieParId";

		} catch (Exception ex) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la categorie n'existe pas"));
			this.indice = false;
			return "rechercherCategorieParId";

		}
	}

	public void edittable(RowEditEvent event) {
		// appel de la méthode
		categorieService.modifierCategorieService((Categorie) event.getObject());

		// récup et mettre à jour la liste
		List<Categorie> liste1 = categorieService.consulatationCategorieService(); 
		catSession.setAttribute("categorieListe", liste1);

	}
	
	
	//consultation de l'ensemble de la liste :
	/*public String consulterListeCategorie()
	
	{
		
	List<Categorie> catOut=categorieService.consulatationCategorieService(agent);
	
	this.listeCat=catOut;
	
	return "listeCat";
		
	
		
	}*/
	

}
