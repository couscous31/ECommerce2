package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService {

	@EJB
	private IClientDao ClientDao;

	@Override
	public List<Client> getAllListClientService(Client cl) {

		return ClientDao.getAllListClient(cl);
	}

	@Override
	public Client addClientService(Client cl) {
		
		return ClientDao.addClient(cl);
	}

	@Override
	public int updateService(Client cl) {
		// TODO Auto-generated method stub
		return ClientDao.update(cl);
	}

	@Override
	public int deleteService(Client cl) {
		// TODO Auto-generated method stub
		return ClientDao.delete(cl);
	}

	@Override
	public Client isExist(Client cl) {
		
		return ClientDao.isExist(cl);
	}
	


}
