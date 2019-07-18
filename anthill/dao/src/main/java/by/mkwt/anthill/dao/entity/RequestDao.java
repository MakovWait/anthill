package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Request;

@Repository
public class RequestDao extends AbstractDao<Request> {

	@Override
	public Class<Request> getEntityClass() {
		return Request.class;
	}

	@Override
	public String getEntityName() {
		return Request.class.getName();
	}

}
