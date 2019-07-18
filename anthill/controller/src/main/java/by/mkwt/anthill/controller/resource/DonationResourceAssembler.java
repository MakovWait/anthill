package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.donation.DonationController;
import by.mkwt.anthill.entity.user.Donation;

@Component
public class DonationResourceAssembler implements ResourceAssembler<Donation, Resource<Donation>> {

	@Override
	public Resource<Donation> toResource(Donation entity) {
		return new Resource<>(entity, linkTo(methodOn(DonationController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(DonationController.class).getAll(new HashMap<String, String>())).withRel("all"));
	}
	
}
