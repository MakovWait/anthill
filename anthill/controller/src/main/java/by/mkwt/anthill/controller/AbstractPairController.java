package by.mkwt.anthill.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import by.mkwt.anthill.controller.util.PageFilterParser;
import by.mkwt.anthill.dao.util.Page;
import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.service.AbstractPairService;

public abstract class AbstractPairController<T extends Identifiable, Y extends Identifiable> {

	protected AbstractPairService<T, Y> projectGenrePairService;
	private ResourceAssembler<Y, Resource<Y>> assembler;

	@Autowired
	protected AbstractPairController(AbstractPairService<T, Y> projectGenrePairService,
			ResourceAssembler<Y, Resource<Y>> assembler) {
		this.projectGenrePairService = projectGenrePairService;
		this.assembler = assembler;
	}

	@GetMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<Resources<Resource<Y>>> getAllByOwner(@PathVariable Integer ownerId,
			@RequestParam Map<String, String> filter) {
		Page page = PageFilterParser.getFromFilter(filter);
		return ResponseEntity.ok(toPageableResource(projectGenrePairService.getAllOwnedByOwner(ownerId, page, filter),
				ownerId, page, filter));
	}

	@DeleteMapping(produces = "application/json; charset=utf-8")
	public void deleteGenreFromProject(@PathVariable Integer ownerId, @RequestBody Integer ownedId) {
		projectGenrePairService.removeOwnedFromOnwer(ownedId, ownerId);
	}

	@PostMapping(produces = "application/json; charset=utf-8")
	public void putDependentByOwner(@PathVariable Integer ownerId, @RequestBody Integer ownedId) {
		projectGenrePairService.addOwnedToOnwer(ownedId, ownerId);
	}

	protected Resources<Resource<Y>> toPageableResource(List<Y> entities, Integer ownerId, Page page,
			Map<String, String> params) {
		Class<? extends AbstractPairController<T, Y>> controller = getControllerClass();
		List<Resource<Y>> resources = new LinkedList<>();
		entities.forEach(e -> resources.add(assembler.toResource(e)));

		Resources<Resource<Y>> resourcesBath = new Resources<Resource<Y>>(resources);

		if (page.getSize() != -1) {
			params.put("page", "1");
			params.put("size", String.valueOf(page.getSize()));

			resourcesBath.add(linkTo(methodOn(controller).getAllByOwner(ownerId, params)).withSelfRel());

			if (page.getNumber() != page.first()) {
				params.put("page", String.valueOf(page.first()));
				resourcesBath.add(linkTo(methodOn(controller).getAllByOwner(ownerId, params)).withRel("first"));
				
				if (page.prev() != page.first()) {
					params.put("page", String.valueOf(page.prev()));
					resourcesBath.add(linkTo(methodOn(controller).getAllByOwner(ownerId, params)).withRel("prev"));
				}
			}

			if (page.getNumber() != page.last()) {
				if (page.next() != page.last()) {
					params.put("page", String.valueOf(page.next()));
					resourcesBath.add(linkTo(methodOn(controller).getAllByOwner(ownerId, params)).withRel("next"));
				}
				
				params.put("page", String.valueOf(page.last()));
				resourcesBath.add(linkTo(methodOn(controller).getAllByOwner(ownerId, params)).withRel("last"));
			}
		} else {
			resourcesBath.add(linkTo(methodOn(controller).getAllByOwner(ownerId, params)).withSelfRel());
		}

		return resourcesBath;
	}

	protected abstract Class<? extends AbstractPairController<T, Y>> getControllerClass();

}
