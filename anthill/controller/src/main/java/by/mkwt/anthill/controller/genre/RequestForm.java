package by.mkwt.anthill.controller.genre;

import by.mkwt.anthill.entity.project.Request;

public class RequestForm {

	private Request request;
	private Integer projectId;
	private Integer userId;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
