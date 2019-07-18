package by.mkwt.anthill.controller.benefit;

import by.mkwt.anthill.entity.project.Benefit;

public class BenefitForm {

	private Benefit benefit;
	private Integer projectId;

	public Benefit getBenefit() {
		return benefit;
	}

	public void setBenefit(Benefit benefit) {
		this.benefit = benefit;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
