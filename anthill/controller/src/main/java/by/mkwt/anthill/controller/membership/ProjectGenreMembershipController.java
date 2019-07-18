package by.mkwt.anthill.controller.membership;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractPairController;
import by.mkwt.anthill.controller.resource.GenreResourceAssembler;
import by.mkwt.anthill.entity.project.Genre;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.service.AbstractPairService;

@RestController
@RequestMapping(value = "/projects/{ownerId}/genres")
public class ProjectGenreMembershipController extends AbstractPairController<Project, Genre> {
	
	@Autowired
	protected ProjectGenreMembershipController(AbstractPairService<Project, Genre> projectGenrePairService, GenreResourceAssembler genreResourceAssembler) {
		super(projectGenrePairService, genreResourceAssembler);
	}

	@Override
	protected Class<? extends AbstractPairController<Project, Genre>> getControllerClass() {
		return this.getClass();
	}

}