package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.membership.ProjectGenreMembershipController;
import by.mkwt.anthill.controller.project.ProjectController;
import by.mkwt.anthill.controller.user.UserController;
import by.mkwt.anthill.entity.project.Project;

@Component
public class ProjectResourceAssembler implements ResourceAssembler<Project, Resource<Project>> {

	@Override
	public Resource<Project> toResource(Project entity) {
		return new Resource<>(entity, 
				linkTo(methodOn(ProjectController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(ProjectController.class).getAll(new HashMap<String, String>())).withRel("all"),
				linkTo(methodOn(ProjectGenreMembershipController.class).getAllByOwner(entity.getId(), new HashMap<String, String>())).withRel("genres")
			);
	}

}
