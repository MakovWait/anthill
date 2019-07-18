package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.user.UserProfileController;
import by.mkwt.anthill.entity.user.UserProfile;

@Component
public class UserProfileResourceAssembler implements ResourceAssembler<UserProfile, Resource<UserProfile>>{

	@Override
	public Resource<UserProfile> toResource(UserProfile entity) {
		return new Resource<>(entity, 
				linkTo(methodOn(UserProfileController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(UserProfileController.class).getAll(new HashMap<String, String>())).withRel("all")
			);
	}

}
