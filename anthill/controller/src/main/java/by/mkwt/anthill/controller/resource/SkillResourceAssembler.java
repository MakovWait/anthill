package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.skill.SkillController;
import by.mkwt.anthill.entity.user.Skill;

@Component
public class SkillResourceAssembler implements ResourceAssembler<Skill, Resource<Skill>> {

	@Override
	public Resource<Skill> toResource(Skill entity) {
		return new Resource<>(entity, linkTo(methodOn(SkillController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(SkillController.class).getAll(new HashMap<String, String>())).withRel("all"));
	}

}
