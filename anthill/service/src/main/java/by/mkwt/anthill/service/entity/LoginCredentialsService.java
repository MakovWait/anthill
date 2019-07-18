package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.LoginCredentials;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.util.ChangePasswordService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class LoginCredentialsService extends AbstractService<LoginCredentials> {

	private ChangePasswordService changePasswordService;

	@Autowired
	public LoginCredentialsService(ChangePasswordService changePasswordService, EntityManagerFactory entityManagerFactory, AbstractDao<LoginCredentials> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
		this.changePasswordService = changePasswordService;
	}

	@Override
	protected void updateEntityLogic(LoginCredentials entity, LoginCredentials with) {
		changePasswordService.changePassword(entity, with);
	}

}
