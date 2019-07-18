package by.mkwt.anthill.entity.project;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.entity.user.UserProfile;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Size;

@Entity
@Table(name = "requests")
public class Request implements Identifiable, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private int id;
	
	@Size(1000)
	@Column(name = "request_comment")
	private String comment;	
	
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "user_id", joinName = "user", attrName = "id", type = Integer.class)
	})
	private User user;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "project_id")
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "project_id", joinName = "project", attrName = "id", type = Integer.class)
	})
	private Project project;

	protected Request() {
		createDate = LocalDateTime.now();
	}
	
	public Request(String comment, User user, Project project) {
		this();
		this.comment = comment;
		this.user = user;
		this.project = project;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", comment=" + comment + ", createDate=" + createDate + ", user=" + user
				+ ", project=" + project + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Request other = (Request) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
