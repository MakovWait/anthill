package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.request.RequestController;
import by.mkwt.anthill.entity.project.Request;

@Component
public class RequestResourceAssembler implements ResourceAssembler<Request, Resource<Request>> {

	public Resource<Request> toResource(Request entity) {
		return new Resource<>(entity, linkTo(methodOn(RequestController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(RequestController.class).getAll(new HashMap<String, String>())).withRel("all"));
	}

}