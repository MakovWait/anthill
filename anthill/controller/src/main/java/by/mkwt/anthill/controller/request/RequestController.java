package by.mkwt.anthill.controller.request;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.controller.genre.RequestForm;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.project.Request;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractService;

@RestController
@RequestMapping(value = "/requests")
public class RequestController extends AbstractController<Request> {

	private AbstractService<Project> projectService;
	private AbstractService<User> userService;

	public RequestController(AbstractService<Request> entityService, AbstractService<User> userService,
			AbstractService<Project> projectService, ResourceAssembler<Request, Resource<Request>> assembler) {
		super(entityService, assembler);
		this.userService = userService;
		this.projectService = projectService;
	}

	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(@RequestBody RequestForm entity) {
		entity.getRequest().setProject(projectService.getById(entity.getProjectId()));
		entity.getRequest().setUser(userService.getById(entity.getUserId()));
		
		return super.add(entity.getRequest());
	}

	@Override
	protected Class<? extends AbstractController<Request>> getControllerClass() {
		return this.getClass();
	}

}
