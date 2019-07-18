package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class UserService extends AbstractService<User>{

	@Autowired
	public UserService(EntityManagerFactory entityManagerFactory, AbstractDao<User> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}

	@Override
	protected void updateEntityLogic(User entity, User with) {
		entity.setAccessLevel(with.getAccessLevel());
		entity.setCash(with.getCash());
		entity.setUsername(with.getUsername());
	}
	
}
