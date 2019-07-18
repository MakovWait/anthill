package by.mkwt.anthill.controller.task;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.project.Note;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.project.Task;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.exception.ValidationException;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController extends AbstractController<Task> {

	private AbstractService<Project> projectService;

	@Autowired
	public TaskController(AbstractService<Task> entityService, AbstractService<Project> projectService, ResourceAssembler<Task, Resource<Task>> assembler) {
		super(entityService, assembler);
		this.projectService = projectService;
	}
	
	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(@RequestBody TaskForm entity) {
		entity.getTask().setProject(projectService.getById(entity.getProjectId()));
		try {
			Task task = entityService.add(entity.getTask());
			Resource<Task> resource = assembler.toResource(task);
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
	protected Class<? extends AbstractController<Task>> getControllerClass() {
		return this.getClass();
	}

}
