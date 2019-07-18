package by.mkwt.anthill.controller.project;

import by.mkwt.anthill.entity.project.Project;

public class ProjectForm {

	private Project project;
	private Integer ownerId;
	private String teamName;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
