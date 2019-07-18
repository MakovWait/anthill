package by.mkwt.anthill.controller.task;

import by.mkwt.anthill.entity.project.Task;

public class TaskForm {

	private Task task;
	private Integer projectId;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
