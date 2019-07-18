package by.mkwt.anthill.controller.donation;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.project.Benefit;
import by.mkwt.anthill.entity.user.Donation;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.service.exception.DonationException;
import by.mkwt.anthill.service.util.DonationProcessService;
import by.mkwt.anthill.validation.exception.ValidationException;

@RestController
@RequestMapping(value = "/donations")
public class DonationController extends AbstractController<Donation> {

	private DonationProcessService donationProcessService;
	private AbstractService<User> userService;
	private AbstractService<Benefit> benefitService;

	@Autowired
	public DonationController(DonationProcessService donationProcessService, AbstractService<Donation> entityService,
			AbstractService<Benefit> benefitService, AbstractService<User> userService,
			ResourceAssembler<Donation, Resource<Donation>> assembler) {
		super(entityService, assembler);
		this.donationProcessService = donationProcessService;
		this.benefitService = benefitService;
		this.userService = userService;
	}

	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(@RequestBody DonationForm entity) {
		try {
			Donation donation = entity.getDonation();
			donation.setBenefit(benefitService.getById(entity.getBenefitId()));
			donation.setUser(userService.getById(entity.getUserId()));
			
			Resource<Donation> resource =  assembler.toResource(donationProcessService.handleDonation(donation));
			
			return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource.getContent());
		} catch (DonationException | ValidationException | AlreadyExistsException | URISyntaxException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	protected Class<? extends AbstractController<Donation>> getControllerClass() {
		return this.getClass();
	}

}
