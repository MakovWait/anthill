package by.mkwt.anthill.controller.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractPairController;
import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractPairService;

@RestController
@RequestMapping(value = "teams/{ownerId}/users")
public class TeamUserMembershipController extends AbstractPairController<Team, User> {

	@Autowired
	protected TeamUserMembershipController(AbstractPairService<Team, User> projectGenrePairService,
			ResourceAssembler<User, Resource<User>> assembler) {
		super(projectGenrePairService, assembler);
	}

	@Override
	protected Class<? extends AbstractPairController<Team, User>> getControllerClass() {
		return this.getClass();
	}

}
