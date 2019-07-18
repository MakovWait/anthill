package by.mkwt.anthill.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.postgresql.translation.messages_bg;

import by.mkwt.anthill.entity.Identifiable;

public class EntityFetcher<T extends Identifiable> {

	public T fetchWith(EntityManager em, T entity, String fieldName, Class<T> entityClass, JoinType joinType) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		
		Root<T> o = q.from(entityClass);
		
		o.fetch(fieldName, joinType);
		q.select(o);
		q.where(cb.and(cb.equal(o.get("id"), entity.getId())));
		
		return em.createQuery(q).getSingleResult();
	}
	
	public T fetchWithFieldById(EntityManager em, Integer entityId, String fieldName, Class<T> entityClass, JoinType joinType) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		
		Root<T> o = q.from(entityClass);
		
		o.fetch(fieldName, joinType);
		q.select(o);
		q.where(cb.equal(o.get("id"), entityId));
		
		return em.createQuery(q).getSingleResult();
	}
	
}
