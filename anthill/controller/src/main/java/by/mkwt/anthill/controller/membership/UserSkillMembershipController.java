package by.mkwt.anthill.controller.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractPairController;
import by.mkwt.anthill.entity.user.Skill;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractPairService;

@RestController
@RequestMapping(value = "/users/{ownerId}/skills")
public class UserSkillMembershipController extends AbstractPairController<User, Skill> {
	
	@Autowired
	protected UserSkillMembershipController(AbstractPairService<User, Skill> projectGenrePairService,
			ResourceAssembler<Skill, Resource<Skill>> assembler) {
		super(projectGenrePairService, assembler);
	}

	@Override
	protected Class<? extends AbstractPairController<User, Skill>> getControllerClass() {
		return this.getClass();
	}

}
