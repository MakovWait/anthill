package by.mkwt.anthill.controller.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.user.Skill;
import by.mkwt.anthill.service.AbstractService;

@RestController
@RequestMapping(value = "/skills")
public class SkillController extends AbstractController<Skill> {

	@Autowired
	public SkillController(AbstractService<Skill> entityService, ResourceAssembler<Skill, Resource<Skill>> assembler) {
		super(entityService, assembler);
	}

	@Override
	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(Skill entity) {
		return super.add(entity);
	}
	
	@Override
	protected Class<? extends AbstractController<Skill>> getControllerClass() {
		return this.getClass();
	}

}
