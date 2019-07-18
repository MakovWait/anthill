package by.mkwt.anthill.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class Page {

	public enum SortDirection {
		ASC, DESC
	}

	protected int number = 1;
	protected int size = -1;
	protected long totalRecords;
	protected SortDirection sortDirection = SortDirection.ASC;
	protected String sortFieldName;

	public Page() {
	}

	public Page(int number, int size) {
		this.number = number;
		this.size = size;
	}

	public <T> TypedQuery<T> createQuery(CriteriaBuilder cb, Root<T> entityRoot, CriteriaQuery<T> entityCriteria,
			EntityManager em) {
		if (getSortFieldName() != null) {
			Path<?> sortPath = entityRoot.get(getSortFieldName());
			entityCriteria.orderBy(isAscSort() ? cb.asc(sortPath) : cb.desc(sortPath));
		}
				
		TypedQuery<T> typedQuery = em.createQuery(entityCriteria);
		
		setTotalRecords(typedQuery.getResultList().size());
		
		if (getSize() != -1) {
			typedQuery.setFirstResult((getNumber() - 1) * getSize());
			typedQuery.setMaxResults(getSize());
		}
		
		return typedQuery;
	}

	public int first() {
		return 1;
	}
	
	public int last() {
		return (int)Math.ceil(((double)getTotalRecords() / (double)getSize()));
	}
	
	public int prev() {
		return number-1;
	}
	
	public int next() {
		return number+1;
	}
	
	public int getNumber() {
		return number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	public boolean isAscSort() {
		return sortDirection.equals(SortDirection.ASC);
	}

	public void setSortFieldName(String sortFieldName) {
		this.sortFieldName = sortFieldName;
	}

	public String getSortFieldName() {
		return sortFieldName;
	}
}
