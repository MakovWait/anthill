package by.mkwt.anthill.controller.team;

import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import by.mkwt.anthill.entity.user.User;

public class TeamResource extends ResourceSupport {

	private String name;
	private Set<User> members;
	
	public TeamResource(String name, Set<User> members) {
		this.name = name;
		this.members = members;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

}
