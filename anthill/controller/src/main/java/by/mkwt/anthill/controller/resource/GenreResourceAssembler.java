package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.genre.GenreController;
import by.mkwt.anthill.entity.project.Genre;

@Component
public class GenreResourceAssembler implements ResourceAssembler<Genre, Resource<Genre>> {

	public Resource<Genre> toResource(Genre entity) {
		return new Resource<>(entity, 
				linkTo(methodOn(GenreController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(GenreController.class).getAll(new HashMap<String, String>())).withRel("all")
				);
	}

}
