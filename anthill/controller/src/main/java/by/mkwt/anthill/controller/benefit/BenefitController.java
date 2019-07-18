package by.mkwt.anthill.controller.benefit;

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
import by.mkwt.anthill.entity.project.Benefit;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.exception.ValidationException;

@RestController
@RequestMapping(value = "/benefits")
public class BenefitController extends AbstractController<Benefit> {

	private AbstractService<Project> projectService;

	@Autowired
	public BenefitController(AbstractService<Benefit> entityService, AbstractService<Project> projectService,
			ResourceAssembler<Benefit, Resource<Benefit>> assembler) {
		super(entityService, assembler);
		this.projectService = projectService;
	}
	
	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(@RequestBody BenefitForm entity) {
		entity.getBenefit().setProject(projectService.getById(entity.getProjectId()));
		try {
			Benefit benefit = entityService.add(entity.getBenefit());
			Resource<Benefit> resource = assembler.toResource(benefit);
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
	protected Class<? extends AbstractController<Benefit>> getControllerClass() {
		return this.getClass();
	}

}
