package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.Donation;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class DonationService extends AbstractService<Donation> {

	@Autowired
	public DonationService(EntityManagerFactory entityManagerFactory, AbstractDao<Donation> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}

	@Override
	protected void updateEntityLogic(Donation entity, Donation with) {
	}

}
