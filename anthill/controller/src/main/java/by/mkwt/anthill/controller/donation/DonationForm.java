package by.mkwt.anthill.controller.donation;

import by.mkwt.anthill.entity.user.Donation;

public class DonationForm {

	private Donation donation;
	private Integer userId;
	private Integer benefitId;

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(Integer benefitId) {
		this.benefitId = benefitId;
	}

}
