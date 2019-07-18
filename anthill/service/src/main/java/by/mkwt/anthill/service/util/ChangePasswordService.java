package by.mkwt.anthill.service.util;

import by.mkwt.anthill.entity.user.LoginCredentials;

public interface ChangePasswordService {
	
	void changePassword(LoginCredentials loginCredentials, LoginCredentials with);
	
}
