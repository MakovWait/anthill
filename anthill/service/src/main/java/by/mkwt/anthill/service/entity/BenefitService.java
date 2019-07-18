package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Benefit;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class BenefitService extends AbstractService<Benefit> {
	
	@Autowired
	public BenefitService(EntityManagerFactory entityManagerFactory, AbstractDao<Benefit> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}

	@Override
	protected void updateEntityLogic(Benefit entity, Benefit with) {
		entity.setBenefitDescription(with.getBenefitDescription());
		entity.setPrice(with.getPrice());
	}
	
}
