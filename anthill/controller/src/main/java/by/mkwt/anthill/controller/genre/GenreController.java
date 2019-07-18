package by.mkwt.anthill.controller.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.project.Genre;
import by.mkwt.anthill.service.AbstractService;

@RestController
@RequestMapping(value = "/genres")
public class GenreController extends AbstractController<Genre> {

	@Autowired
	public GenreController(AbstractService<Genre> genreService, ResourceAssembler<Genre, Resource<Genre>> assembler) {
		super(genreService, assembler);
	}

	@Override
	@PostMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> add(@RequestBody Genre entity) {
		return super.add(entity);
	}

	@Override
	protected Class<? extends AbstractController<Genre>> getControllerClass() {
		return this.getClass();
	}
}
