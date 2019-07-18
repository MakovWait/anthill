package by.mkwt.anthill.controller.user;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.service.exception.RegistrationException;
import by.mkwt.anthill.service.util.RegistrationService;
import by.mkwt.anthill.validation.exception.ValidationException;

@RestController
@RequestMapping(value = "/users")
public class UserController extends AbstractController<User> {

	private RegistrationService registrationService;

	public UserController(RegistrationService registrationService, AbstractService<User> entityService, ResourceAssembler<User, Resource<User>> assembler) {
		super(entityService, assembler);
		this.registrationService = registrationService;
	}
	
	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(@RequestBody UserRegistrationForm entity) {
		try {
			registrationService.registration(entity.getUser(), entity.getLoginCredentials(), entity.getUserProfile());
			return ResponseEntity.ok().build();
		} catch (RegistrationException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@Override
	protected Class<? extends AbstractController<User>> getControllerClass() {
		return this.getClass();
	}

}
