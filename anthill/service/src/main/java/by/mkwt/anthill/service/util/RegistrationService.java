package by.mkwt.anthill.service.util;

import by.mkwt.anthill.entity.user.LoginCredentials;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.entity.user.UserProfile;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.service.exception.RegistrationException;
import by.mkwt.anthill.validation.exception.ValidationException;

public interface RegistrationService {

	void registration(User user, LoginCredentials loginCredentials, UserProfile userProfile) throws RegistrationException;
	
}
