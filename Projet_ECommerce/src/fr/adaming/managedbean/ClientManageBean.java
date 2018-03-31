package fr.adaming.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManageBean implements Serializable {

	@EJB // Transformation UML en Java
	IClientService clService;

	// Declarer comme un attribut
	private Client client;

	HttpSession maSession;

	// Constructeurs
	public ClientManageBean() {
		this.client = new Client();
	}

	// Guetter et Setter
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	// Methode client

	public String ajoutcl() {

		Client clAjout = clService.addClientService(this.client);

		if (clAjout.getIdClient() != 0) {

			// Recuperer la nouvelle liste
			List<Client> liste = clService.getAllListClientService(this.client);
			maSession.setAttribute("clientliste", liste);

			return "accueil";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout du client a echoué"));
			return "ajouterclient";
		}
	}

	public String ModifierClient() {

		int verif = clService.updateService(this.client);

		if (verif != 0) {
			// Recuperer la nouvelle liste
			List<Client> liste = clService.getAllListClientService(this.client);

			maSession.setAttribute("clientliste", liste);

			return "accueil";
		} else {

			return "modifierclient";
		}
	}

	public String SupprimerClient() {

		int verif = clService.deleteService(this.client);

		if (verif != 0) {
			// Recuperer la nouvelle liste
			List<Client> liste = clService.getAllListClientService(this.client);
			maSession.setAttribute("clientliste", liste);

			return "accueil";
		} else {
			return "supprimer";
		}
	}
	
}
