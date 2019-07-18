package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.UserProfile;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class UserProfileService extends AbstractService<UserProfile> {

	@Autowired
	public UserProfileService(EntityManagerFactory entityManagerFactory, AbstractDao<UserProfile> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}

	@Override
	protected void updateEntityLogic(UserProfile entity, UserProfile with) {
		entity.setAbout(with.getAbout());
		entity.setBirthDate(with.getBirthDate());
		entity.setEmail(with.getEmail());
		entity.setFirstname(with.getFirstname());
		entity.setLastname(with.getLastname());
	}

}
