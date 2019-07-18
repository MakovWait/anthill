package by.mkwt.anthill.entity.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.validation.annotation.NotEmptyString;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Size;

@Entity
@Table(name = "skills")
public class Skill implements Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_id")
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "skill_id", attrName = "id", type = Integer.class)
	})
	private int id;
	
	@NotNull
	@NotEmptyString
	@Size(value = 30)
	@Column(name = "skill_name")
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "skill_name", attrName = "name")
	})
	private String name;
	
	@ManyToMany(mappedBy = "skills")
	@JsonIgnore
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "user_id", joinName = "users", attrName = "id", type = Integer.class)
	})
	private Set<User> users;
	
	protected Skill() {}
	
	public Skill(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Skill))
			return false;
		Skill other = (Skill) obj;
		
		return this.getName().equals(other.getName());
	}
	
}
