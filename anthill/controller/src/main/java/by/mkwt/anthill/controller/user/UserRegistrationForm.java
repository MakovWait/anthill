package by.mkwt.anthill.controller.user;

import by.mkwt.anthill.entity.user.LoginCredentials;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.entity.user.UserProfile;

public class UserRegistrationForm {

	private User user;
	private LoginCredentials loginCredentials;
	private UserProfile userProfile;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginCredentials getLoginCredentials() {
		return loginCredentials;
	}

	public void setLoginCredentials(LoginCredentials loginCredentials) {
		this.loginCredentials = loginCredentials;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "UserRegistrationForm [user=" + user + ", loginCredentials=" + loginCredentials + ", userProfile="
				+ userProfile + "]";
	}
	
}
