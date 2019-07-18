package by.mkwt.anthill.controller.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractPairController;
import by.mkwt.anthill.controller.resource.ProjectResourceAssembler;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractPairService;

@RestController
@RequestMapping(value = "/owners/{ownerId}/projects")
public class OwnerProjectsMembershipController extends AbstractPairController<User, Project> {

	@Autowired
	protected OwnerProjectsMembershipController(AbstractPairService<User, Project> projectGenrePairService, ProjectResourceAssembler projectResourceAssembler) {
		super(projectGenrePairService, projectResourceAssembler);
	}

	@Override
	protected Class<? extends AbstractPairController<User, Project>> getControllerClass() {
		return this.getClass();
	}

}
