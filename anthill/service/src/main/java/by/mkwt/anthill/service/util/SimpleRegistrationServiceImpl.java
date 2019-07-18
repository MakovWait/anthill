package by.mkwt.anthill.service.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.entity.user.LoginCredentials;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.entity.user.UserProfile;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.service.exception.RegistrationException;
import by.mkwt.anthill.validation.exception.ValidationException;

@Service
public class SimpleRegistrationServiceImpl implements RegistrationService {
	
	private AbstractService<User> userService;
	private AbstractService<LoginCredentials> loginCredentialsService;
	private AbstractService<UserProfile> userProfileService;
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	public SimpleRegistrationServiceImpl(
			EntityManagerFactory entityManagerFactory,
			AbstractService<User> userService, 
			AbstractService<LoginCredentials> loginCredentialsService, 
			AbstractService<UserProfile> userProfileService) {
				this.entityManagerFactory = entityManagerFactory;
				this.userService = userService;
				this.loginCredentialsService = loginCredentialsService;
				this.userProfileService = userProfileService;
	}

	@Override
	public void registration(User user, LoginCredentials loginCredentials, UserProfile userProfile) throws RegistrationException {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		try {
			User persistedUser = userService.addWithoutCommit(em, user);
			loginCredentials.setUser(persistedUser);
			loginCredentialsService.addWithoutCommit(em, loginCredentials);
			userProfile.setUser(persistedUser);
			userProfileService.addWithoutCommit(em, userProfile);
			em.getTransaction().commit();
		} catch (ValidationException | AlreadyExistsException e) {
			em.getTransaction().rollback();
			throw new RegistrationException(e.getMessage());
		} finally {
			em.close();
		}
		
	}
	
}
