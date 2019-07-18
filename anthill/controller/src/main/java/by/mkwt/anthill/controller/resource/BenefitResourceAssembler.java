package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.benefit.BenefitController;
import by.mkwt.anthill.entity.project.Benefit;

@Component
public class BenefitResourceAssembler implements ResourceAssembler<Benefit, Resource<Benefit>> {

	@Override
	public Resource<Benefit> toResource(Benefit entity) {
		return new Resource<>(entity, linkTo(methodOn(BenefitController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(BenefitController.class).getAll(new HashMap<String, String>())).withRel("all"));
	}
	
}
