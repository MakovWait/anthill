package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.LoginCredentials;

@Repository
public class LoginCredentialsDao extends AbstractDao<LoginCredentials> {

	@Override
	public Class<LoginCredentials> getEntityClass() {
		return LoginCredentials.class;
	}

	@Override
	public String getEntityName() {
		return LoginCredentials.class.getName();
	}

}
