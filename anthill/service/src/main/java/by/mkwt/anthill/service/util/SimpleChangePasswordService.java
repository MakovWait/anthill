package by.mkwt.anthill.service.util;

import org.springframework.stereotype.Service;

import by.mkwt.anthill.entity.user.LoginCredentials;

@Service
public class SimpleChangePasswordService implements ChangePasswordService {

	@Override
	public void changePassword(LoginCredentials loginCredentials, LoginCredentials with) {
		loginCredentials.setPassword(with.getPassword());
	}
	
}
