package by.mkwt.anthill.controller.user;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.user.LoginCredentials;
import by.mkwt.anthill.service.AbstractService;

@RestController
@RequestMapping(value = "/logincredentials")
public class LoginCredentialsController extends AbstractController<LoginCredentials> {

	public LoginCredentialsController(AbstractService<LoginCredentials> entityService,
			ResourceAssembler<LoginCredentials, Resource<LoginCredentials>> assembler) {
		super(entityService, assembler);
	}
	
	@Override
	protected Class<? extends AbstractController<LoginCredentials>> getControllerClass() {
		return this.getClass();
	}

}
