package by.mkwt.anthill.entity.project;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.validation.annotation.NotEmptyString;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Size;

@Entity
@Table(name = "project_tasks")
public class Task implements Identifiable, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private int id;
	
	@NotNull
	@NotEmptyString
	@Size(value = 50)
	@Column(name = "task_title")
	private String title;
	
	@NotNull
	@NotEmptyString
	@Column(name = "task_description")
	private String description;	
	
	@Column(name = "is_finished")
	private boolean isFinished;
	
	@NotNull
	@Column(name = "create_date")
	private Timestamp createDate;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "project_id")
	@FilterSchema(filterFields = {
			@FilterField(mappedBy = "project_id", attrName = "id", joinName = "project", type = Integer.class)
	})
	private Project project;
	
	public Task(String title, String description, Project project) {
		this();
		this.title = title;
		this.description = description;
		this.project = project;
	}
	
	protected Task() {
		createDate = new Timestamp(new Date().getTime());
		isFinished = false;
	}
	
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", isFinished=" + isFinished
				+ ", createDate=" + createDate + ", project=" + project + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isFinished ? 1231 : 1237);
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Task other = (Task) obj;
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
		if (isFinished != other.isFinished)
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
