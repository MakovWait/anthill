package by.mkwt.anthill.entity.project;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonRootName;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.validation.annotation.NotEmptyString;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Size;

@Entity
@Table(name = "genres")
@JsonRootName(value = "genre")
public class Genre implements Identifiable, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
	@JsonProperty(access = Access.READ_ONLY)
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "genre_id", attrName = "id", type = Integer.class)
	})
	private int id;
	
	@NotNull
	@NotEmptyString
	@Size(30)
	@Column(name = "genre_name")
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "genre_name", attrName = "name")
	})
	private String name;
	
	@ManyToMany
	@JoinTable(name = "project_genres", joinColumns = @JoinColumn(name = "genre_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	@JsonIgnore
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "project_name", joinName = "projects", attrName = "name"),
			@FilterField(mappedBy = "project_id", joinName = "projects", attrName = "id", type = Integer.class)
	})
	private Set<Project> projects;
	
	public Genre(String name) {
		this.name = name;
	}

	protected Genre() {
		
	}
	
	public Genre(int id, String name) {
		this.id = id;
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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Genre other = (Genre) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}

