package by.mkwt.anthill.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.JoinType;

import by.mkwt.anthill.dao.util.Page;
import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.filter.exception.FilterException;

public abstract class AbstractPairService<T extends Identifiable, Y extends Identifiable> {	
	
	protected AbstractService<T> ownerService;
	protected AbstractService<Y> ownedService;
	private EntityManagerFactory entityManagerFactory;
	
	private String fetchFieldName;
	
	public AbstractPairService(EntityManagerFactory entityManagerFactory, AbstractService<T> ownerService, AbstractService<Y> ownedService, String fetchFieldName) {
		this.entityManagerFactory = entityManagerFactory;
		this.ownerService = ownerService;
		this.ownedService = ownedService;
		this.fetchFieldName = fetchFieldName;
	}

	public void addOwnedToOnwer(Integer ownedId, Integer ownerId) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		T p = ownerService.getAttachedById(em, ownerId);
		p = ownerService.attachedFetchWith(em, p.getId(), fetchFieldName, JoinType.INNER);
		addLogic(p, ownedService.getById(ownedId));
		em.getTransaction().commit();
		em.close();
	}

	public void removeOwnedFromOnwer(Integer ownedId, Integer ownerId) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		T p = ownerService.getAttachedById(em, ownerId);
		p = ownerService.attachedFetchWith(em, p.getId(), fetchFieldName, JoinType.INNER);
		removeLogic(p, ownedService.getById(ownedId));
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Y> getAllOwnedByOwner(Integer ownerId, Page page, Map<String, String> filter) {
		allOwnedByOwnerFilterLogic(ownerId, filter);
		
		try {
			List<Y> result = ownedService.getAll(page, filter);
			clearFilterLogic(filter);
			return result;
		} catch (FilterException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	protected abstract void addLogic(T owner, Y owned);

	protected abstract void removeLogic(T owner, Y owned);
	
	protected abstract void allOwnedByOwnerFilterLogic(Integer ownerId, Map<String, String> filter);

	protected abstract void clearFilterLogic(Map<String, String> filter);
	
}
