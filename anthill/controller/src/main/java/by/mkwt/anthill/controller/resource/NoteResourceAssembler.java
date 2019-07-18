package by.mkwt.anthill.controller.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import by.mkwt.anthill.controller.note.NoteController;
import by.mkwt.anthill.entity.project.Note;

@Component
public class NoteResourceAssembler implements ResourceAssembler<Note, Resource<Note>> {

	@Override
	public Resource<Note> toResource(Note entity) {
		return new Resource<>(entity, linkTo(methodOn(NoteController.class).getOne(entity.getId())).withSelfRel(),
				linkTo(methodOn(NoteController.class).getAll(new HashMap<String, String>())).withRel("all"));
	}
}
