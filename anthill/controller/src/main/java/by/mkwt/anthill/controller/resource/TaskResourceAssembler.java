package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.task.TaskController;
import by.mkwt.anthill.entity.project.Task;

@Component
public class TaskResourceAssembler implements ResourceAssembler<Task, Resource<Task>> {

	@Override
	public Resource<Task> toResource(Task entity) {
		return new Resource<>(entity, linkTo(methodOn(TaskController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(TaskController.class).getAll(new HashMap<String, String>())).withRel("all"));
	}
	
}
