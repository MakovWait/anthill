package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.team.TeamController;
import by.mkwt.anthill.entity.project.Team;

@Component
public class TeamResourceAssembler implements ResourceAssembler<Team, Resource<Team>> {

	@Override
	public Resource<Team> toResource(Team entity) {
		return new Resource<>(entity, linkTo(methodOn(TeamController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(TeamController.class).getAll(new HashMap<String, String>())).withRel("all"));
	}
	
}
