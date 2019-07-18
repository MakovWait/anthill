package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.user.LoginCredentialsController;
import by.mkwt.anthill.entity.user.LoginCredentials;

@Component
public class LoginCredentialsAssembler implements ResourceAssembler<LoginCredentials, Resource<LoginCredentials>> {

	@Override
	public Resource<LoginCredentials> toResource(LoginCredentials entity) {
		return new Resource<>(entity,
				linkTo(methodOn(LoginCredentialsController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(LoginCredentialsController.class).getAll(new HashMap<String, String>())).withRel("all"));
	}
	
}
