package by.mkwt.anthill.entity.user;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.fasterxml.jackson.annotation.JsonIgnore;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.project.Request;
import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.validation.annotation.Positive;
import by.mkwt.anthill.validation.annotation.Size;
import by.mkwt.anthill.validation.annotation.NotEmptyString;
import by.mkwt.anthill.validation.annotation.NotNull;

@Entity
@Table(name = "users")
public class User implements Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@NotNull
	@NotEmptyString
	@Size(value = 100)
	private String username;
	
	@Positive
	private BigDecimal cash;
	
	@NotNull
	@Column(name = "access_level")
	private int accessLevel;
	
	@ManyToMany
	@JoinTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	@JsonIgnore
	private Set<Skill> skills = new HashSet<Skill>();
	
	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private LoginCredentials loginCredentials;
	
	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private UserProfile userProfile;
	
	@OneToMany(mappedBy = "owner")
	@JsonIgnore
	private Set<Project> projects;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Donation> donations;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Request> requests;
	
	@ManyToMany(mappedBy = "members")
	@JsonIgnore
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "team_id", joinName = "teams", attrName = "id", type = Integer.class)
	})
	private Set<Team> teams;

	protected User() {
		
	}

	public User(String username, int accessLevel) {
		this.username = username;
		this.accessLevel = accessLevel;
		this.cash = new BigDecimal(0);
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", cash=" + cash + ", accessLevel=" + accessLevel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
