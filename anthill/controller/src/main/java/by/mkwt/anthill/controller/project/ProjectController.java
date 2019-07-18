package by.mkwt.anthill.controller.project;

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
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.RegistrationException;
import by.mkwt.anthill.service.util.ProjectRegistrationService;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController extends AbstractController<Project> {

	private ProjectRegistrationService projectRegistrationService;
	private AbstractService<User> userService;

	@Autowired
	public ProjectController(ProjectRegistrationService projectRegistrationService,
			AbstractService<Project> projectService, AbstractService<User> userService,
			ResourceAssembler<Project, Resource<Project>> assembler) {
		super(projectService, assembler);
		this.projectRegistrationService = projectRegistrationService;
		this.userService = userService;
	}

	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(@RequestBody ProjectForm entity) {
		try {
			Project project = projectRegistrationService.registerProject(entity.getProject(), new Team(entity.getTeamName()), userService.getById(entity.getOwnerId()));
			Resource<Project> resource = assembler.toResource(project);
			return ResponseEntity.created(new URI(resource.getId().getHref())).body(project);
		} catch (RegistrationException | URISyntaxException e) {			
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	protected Class<? extends AbstractController<Project>> getControllerClass() {
		return this.getClass();
	}

}
