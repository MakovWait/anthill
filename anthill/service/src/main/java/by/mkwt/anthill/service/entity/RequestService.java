package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Request;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class RequestService extends AbstractService<Request> {

	@Autowired
	public RequestService(EntityManagerFactory entityManagerFactory, AbstractDao<Request> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}

	@Override
	protected void updateEntityLogic(Request entity, Request with) {
	}

}
