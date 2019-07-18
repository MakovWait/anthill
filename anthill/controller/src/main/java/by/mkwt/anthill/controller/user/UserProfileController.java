package by.mkwt.anthill.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.user.UserProfile;
import by.mkwt.anthill.service.AbstractService;

@RestController
@RequestMapping(value = "/userprofiles")
public class UserProfileController extends AbstractController<UserProfile> {

	@Autowired
	public UserProfileController(AbstractService<UserProfile> entityService,
			ResourceAssembler<UserProfile, Resource<UserProfile>> assembler) {
		super(entityService, assembler);
	}

	@Override
	protected Class<? extends AbstractController<UserProfile>> getControllerClass() {
		return this.getClass();
	}

}
