package by.mkwt.anthill.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.JoinType;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.dao.EntityFetcher;
import by.mkwt.anthill.dao.util.Page;
import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.filter.exception.FilterException;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.ValidationHandler;
import by.mkwt.anthill.validation.exception.ValidationException;

public abstract class AbstractService<T extends Identifiable> {

	private EntityManagerFactory entityManagerFactory;
	private ValidationHandler validationHandler;
	protected AbstractDao<T> entityDao;
	protected EntityFetcher<T> entityFetcher;

	public AbstractService(EntityManagerFactory entityManagerFactory, AbstractDao<T> entityDao,
			ValidationHandler validationHandler) {
		this.entityManagerFactory = entityManagerFactory;
		this.entityDao = entityDao;
		this.validationHandler = validationHandler;
		this.entityFetcher = new EntityFetcher<T>();
	}

	public T update(T entity, T with) throws ValidationException, AlreadyExistsException {
		validate(with);
		updateEntityLogic(entity, with);

		EntityManager em = getEntityManager();
		T result = null;

		try {
			em.getTransaction().begin();
			result = entityDao.update(em, entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new AlreadyExistsException();
		} finally {
			em.close();
		}

		return result;
	}

	public T add(T entity) throws AlreadyExistsException, ValidationException {
		validate(entity);
		EntityManager em = getEntityManager();

		T result = null;

		em.getTransaction().begin();
		try {
			result = entityDao.persiste(em, entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new AlreadyExistsException();
		}

		em.close();

		return result;
	}

	public T addWithoutCommit(EntityManager em, T entity) throws ValidationException, AlreadyExistsException {
		validate(entity);
		
		T result = null;
		
		try {
			result =  entityDao.persiste(em, entity);
		} catch (Exception e) {
			throw new AlreadyExistsException();
		}
		
		return result;
	}
	
	public T getById(int id) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		T result = entityDao.readOne(em, id);
		tx.commit();

		em.close();
		return result;
	}

	protected T getAttachedById(EntityManager em, int id) {
		return entityDao.readOne(em, id);
	}

	public List<T> getAll() {
		return getAll(new Page());
	}

	public List<T> getAll(Page page) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		List<T> result = entityDao.readAll(em, page);
		tx.commit();

		em.close();
		return result;
	}

	public List<T> getAll(Page page, Map<String, String> filter) throws FilterException {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		List<T> result = entityDao.readAll(em, page, filter);
		tx.commit();

		em.close();
		return result;
	}

	private void delete(T entity) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		entityDao.delete(em, entity);
		tx.commit();

		em.close();
	}

	public void deleteById(int id) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		entityDao.delete(em, getAttachedById(em, id));
		tx.commit();

		em.close();
	}

	protected void validate(Object entity) throws ValidationException {
		validationHandler.validate(entity);
	}

	public T fetchWith(int entityId, String fieldName, JoinType joinType) {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		T entity = null;

		try {
//			entity = attachedFetchWith(em, entityId, fieldName, joinType);
			entity = entityFetcher.fetchWithFieldById(em, entityId, fieldName, entityDao.getEntityClass(), joinType);
			tx.commit();
		} catch (NoResultException e) {
			// TODO ADD NOT FOUND
			e.printStackTrace();
		} finally {
			em.close();
		}

		return entity;
	}

	public T attachedFetchWith(EntityManager em, int entityId, String fieldName, JoinType joinType) {
		T entity = getAttachedById(em, entityId);

		try {
			entity = entityFetcher.fetchWithFieldById(em, entityId, fieldName, entityDao.getEntityClass(), joinType);
		} catch (NoResultException e) {
			// TODO ADD NOT FOUND
			e.printStackTrace();
		}

		return entity;
	}

	protected EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	protected abstract void updateEntityLogic(T entity, T with);

}
