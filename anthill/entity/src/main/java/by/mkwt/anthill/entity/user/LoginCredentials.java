package by.mkwt.anthill.entity.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.validation.annotation.NotEmptyString;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Size;

@Entity
@Table(name = "login_credentials")
public class LoginCredentials implements Identifiable, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "login_credentials_id")
	private int id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@NotNull
	@NotEmptyString
	@Size(200)
	@Column(name = "user_password")
	private String password;
	@Size(50)
	@Column(name = "password_salt")
	private String passwordSalt;
	@Size(50)
	@Column(name = "password_hash_algoritm")
	private String hashMethodName;

	public LoginCredentials(User user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public LoginCredentials(User user, String password, String passwordSalt, String hashMethodName) {
		this.user = user;
		this.password = password;
		this.passwordSalt = passwordSalt;
		this.hashMethodName = hashMethodName;
	}
	
	protected LoginCredentials() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getHashMethodName() {
		return hashMethodName;
	}

	public void setHashMethodName(String hashMethodName) {
		this.hashMethodName = hashMethodName;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LoginCredentials [password=" + password + ", passwordSalt=" + passwordSalt + ", hashMethodName="
				+ hashMethodName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashMethodName == null) ? 0 : hashMethodName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordSalt == null) ? 0 : passwordSalt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginCredentials other = (LoginCredentials) obj;
		if (hashMethodName == null) {
			if (other.hashMethodName != null)
				return false;
		} else if (!hashMethodName.equals(other.hashMethodName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordSalt == null) {
			if (other.passwordSalt != null)
				return false;
		} else if (!passwordSalt.equals(other.passwordSalt))
			return false;
		return true;
	}
	
}
