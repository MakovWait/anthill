package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.membership.UserSkillMembershipController;
import by.mkwt.anthill.controller.user.UserController;
import by.mkwt.anthill.entity.user.User;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

	@Override
	public Resource<User> toResource(User entity) {
		return new Resource<>(entity, 
				linkTo(methodOn(UserController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getAll(new HashMap<String, String>())).withRel("all"),
				linkTo(methodOn(UserSkillMembershipController.class).getAllByOwner(entity.getId(), new HashMap<String, String>())).withRel("skills")
			);
	}
}
