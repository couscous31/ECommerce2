package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAgentDao;
import fr.adaming.model.Agent;

@Stateful
public class AgentServiceImpl implements IAgentService {
	
	@EJB //Annotation pour injection de dependance
	private IAgentDao agentDao;

	@Override
	public Agent isExist(Agent a) {
		
		return agentDao.isExist(a);
	}

	

}
