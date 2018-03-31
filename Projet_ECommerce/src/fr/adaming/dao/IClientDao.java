package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientDao {
	
	public List<Client> getAllListClient(Client cl);
	
	public Client addClient(Client cl);
	
	public int update(Client cl);
	
	public int delete(Client cl);
	
	public Client isExist(Client cl);
	

}
