package by.mkwt.anthill.entity.project;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Positive;
import by.mkwt.anthill.validation.annotation.Size;

@Entity
@Table(name = "projects")
public class Project implements Identifiable, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	@JsonProperty(access = Access.READ_ONLY)
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "project_id", attrName = "id", type = Integer.class)
	})
	private int id;
	
	@NotNull
	@Size(value = 30)
	@Column(name = "project_name")
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "name", attrName = "name")
	})
	private String name;
	
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "createDate", attrName = "createDate", type = LocalDateTime.class)
	})
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@NotNull
	private String description;
	
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "goalCash", attrName = "goalCash", type = BigDecimal.class)
	})
	@Positive
	@Column(name = "goal_cash")
	private BigDecimal goalCash;
	
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "isArchived", attrName = "isArchived", type = Boolean.class)
	})
	@Column(name = "is_archived")
	private boolean isArchived;
	
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "owner_name", joinName = "owner", attrName = "username"),
			@FilterField(mappedBy = "owner_id", joinName = "owner", attrName = "id", type = Integer.class)
	})
	@NotNull
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "genre_name", joinName = "genres", attrName = "name"),
			@FilterField(mappedBy = "genre_id", joinName = "genres", attrName = "id", type = Integer.class)
	})
	@ManyToMany
	@JoinTable(name = "project_genres", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@JsonIgnore
	private Set<Genre> genres;
	
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "team_name", joinName = "team", attrName = "name")
	})
	@OneToOne
	@JoinColumn(name = "team_id")
	@NotNull
	private Team team;
	
	@OneToMany(mappedBy = "project")
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "note_create_date", joinName = "notes", attrName = "createDate", type = LocalDateTime.class),
			@FilterField(mappedBy = "note_title", joinName = "notes", attrName = "title")
	})
	@JsonIgnore
	private Set<Note> notes;
	
	@OneToMany(mappedBy = "project")
	@JsonIgnore
	private Set<Task> tasks;
	
	@OneToMany(mappedBy = "project")
	@JsonIgnore
	private Set<Benefit> benefits;
	
	@OneToMany(mappedBy = "project")
	@JsonIgnore
	private Set<Request> requests;
	
	public Project(String name, String description, User owner) {
		this();
		this.name = name;
		this.description = description;
		this.owner = owner;
	}
	
	public Project(String name, String description, BigDecimal goalCash, User owner) {
		this(name, description, owner);
		this.goalCash = goalCash;
	}

	protected Project() {
		isArchived = false;
		createDate = LocalDateTime.now();
		goalCash = new BigDecimal(0);
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getGoalCash() {
		return goalCash;
	}

	public void setGoalCash(BigDecimal goalCash) {
		this.goalCash = goalCash;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Benefit> getBenefits() {
		return benefits;
	}

	public void setBenefits(Set<Benefit> benefits) {
		this.benefits = benefits;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}

	public boolean isArchived() {
		return isArchived;
	}

	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", createDate=" + createDate + ", description=" + description
				+ ", goalCash=" + goalCash + ", isArchived=" + isArchived + ", owner=" + owner + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((goalCash == null) ? 0 : goalCash.hashCode());
		result = prime * result + (isArchived ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Project other = (Project) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (goalCash == null) {
			if (other.goalCash != null)
				return false;
		} else if (!goalCash.equals(other.goalCash))
			return false;
		if (isArchived != other.isArchived)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	
}
