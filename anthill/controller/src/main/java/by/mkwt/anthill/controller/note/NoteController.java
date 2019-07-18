package by.mkwt.anthill.controller.note;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.project.Note;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.exception.ValidationException;

@RestController
@RequestMapping("/notes")
public class NoteController extends AbstractController<Note> {

	private AbstractService<Project> projectService;

	@Autowired
	public NoteController(AbstractService<Note> noteService, AbstractService<Project> projectService,
			ResourceAssembler<Note, Resource<Note>> assembler) {
		super(noteService, assembler);
		this.projectService = projectService;
	}

	@Override
	public ResponseEntity<?> add(Note entity) {
		return super.add(entity);
	}

	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(@RequestBody NoteForm entity) {
		entity.getNote().setProject(projectService.getById(entity.getProjectId()));
		try {
			Note note = entityService.add(entity.getNote());
			Resource<Note> resource = assembler.toResource(note);
			return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
		} catch (AlreadyExistsException | ValidationException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	protected Class<? extends AbstractController<Note>> getControllerClass() {
		return this.getClass();
	}

}
