package by.mkwt.anthill.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.mkwt.anthill.dao.util.Page;
import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.filter.Filter;
import by.mkwt.anthill.filter.FilterQueryParser;
import by.mkwt.anthill.filter.exception.FilterException;

public abstract class AbstractDao<T extends Identifiable> {

	private Filter<T> filter;
	private FilterQueryParser parser;

	public AbstractDao() {
		filter = new Filter<T>();
		parser = new FilterQueryParser();
	}
	
	public T persiste(EntityManager em, T entity) {
		em.persist(entity);
		return entity;
	}

	public T update(EntityManager em, T entity) {
		return em.merge(entity);
	}

	public void delete(EntityManager em, T entity) {
		em.remove(entity);
	}

	public T readOne(EntityManager em, int id) {
		return (T) em.find(getEntityClass(), id);
	}
	
	public List<T> readAll(EntityManager em, Page page) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> entityCriteria = cb.createQuery(getEntityClass());

		Root<T> entityRoot = entityCriteria.from(getEntityClass());

		return page.createQuery(cb, entityRoot, entityCriteria, em).getResultList();
	}

	public List<T> readAll(EntityManager em, Page page, Map<String, String> filterParams) throws FilterException {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<T> q = cb.createQuery(getEntityClass());
		Root<T> root = q.from(getEntityClass());
		
		filter.fillCretiriaQueryWithFilterParams(parser.parse(filterParams), cb, q, root, getEntityClass());
				
		return page.createQuery(cb, root, q, em).getResultList();
	}

	public Long getCount(EntityManager em) {
		CriteriaQuery<Long> criteriaQuery = em.getCriteriaBuilder().createQuery(Long.class);
		criteriaQuery.select(em.getCriteriaBuilder().count(criteriaQuery.from(getEntityClass())));
		return em.createQuery(criteriaQuery).getSingleResult();
	}

	public abstract Class<T> getEntityClass();

	public abstract String getEntityName();

}
