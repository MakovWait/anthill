package by.mkwt.anthill.service.util;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.entity.project.Benefit;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.user.Donation;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.service.exception.DonationException;
import by.mkwt.anthill.validation.exception.ValidationException;

@Service
public class SimpleDonationProcessService implements DonationProcessService {

	private AbstractService<User> userService;
	private EntityManagerFactory entityManagerFactory;
	private AbstractService<Donation> donationService;
	private AbstractService<Benefit> benefitService;
	private AbstractService<Project> projectService;

	@Autowired
	public SimpleDonationProcessService(EntityManagerFactory entityManagerFactory, AbstractService<User> userService,
			AbstractService<Donation> donationService, AbstractService<Benefit> benefitService,
			AbstractService<Project> projectService) {
		this.entityManagerFactory = entityManagerFactory;
		this.userService = userService;
		this.donationService = donationService;
		this.benefitService = benefitService;
		this.projectService = projectService;
	}

	@Override
	public Donation handleDonation(Donation donation) throws ValidationException, AlreadyExistsException, DonationException {
		User user = donation.getUser();
		Benefit benefit = donation.getBenefit();

		if (donation.getValue().compareTo(benefit.getPrice()) >= 0) {
			if (user.getCash().compareTo(donation.getValue()) >= 0) {
				
				user.setCash(user.getCash().subtract(donation.getValue()));
				
				userService.update(userService.getById(user.getId()), user);
				donationService.add(donation);
			} else {
				throw new DonationException("User cash is low");				
			}
		} else {
			throw new DonationException("Donation value is low");
		}
		
		return donation;
	}

}
