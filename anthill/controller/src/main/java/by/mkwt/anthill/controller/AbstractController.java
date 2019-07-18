package by.mkwt.anthill.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import by.mkwt.anthill.controller.util.PageFilterParser;
import by.mkwt.anthill.dao.util.Page;
import by.mkwt.anthill.entity.Identifiable;
import by.mkwt.anthill.filter.exception.FilterException;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.exception.ValidationException;

public abstract class AbstractController<T extends Identifiable> {

	protected Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

	protected AbstractService<T> entityService;
	protected ResourceAssembler<T, Resource<T>> assembler;

	public AbstractController(AbstractService<T> entityService, ResourceAssembler<T, Resource<T>> assembler) {
		this.entityService = entityService;
		this.assembler = assembler;
	}

	@GetMapping(value = "/{id}", produces = "application/json; charset=utf-8")
	public ResponseEntity<?> getOne(@PathVariable int id) {
		T entity = entityService.getById(id);

		if (entity != null) {
			return ResponseEntity.ok(assembler.toResource(entity));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(produces = "application/json; charset=utf-8")
	public ResponseEntity<?> getAll(@RequestParam Map<String, String> filter) {
		Page p = PageFilterParser.getFromFilter(filter);
		try {
			List<T> entities = entityService.getAll(p, filter);

			if (entities.size() == 0) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(toPageableResource(entities, p, filter));
		} catch (FilterException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(new Resource<>(e.getMessage()));
		}
	}

	public ResponseEntity<?> add(@RequestBody T entity) {
		try {
			T createdEntity = entityService.add(entity);
			Resource<T> resource = assembler.toResource(createdEntity);

			return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
		} catch (AlreadyExistsException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(new Resource<>(e.getMessage()));
		} catch (ValidationException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(new Resource<>(e.getMessage()));
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping(value = "/{id}", produces = "application/json; charset=utf-8")
	public ResponseEntity<?> deleteOne(@PathVariable int id) {
		entityService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/{id}", produces = "application/json; charset=utf-8")
	public ResponseEntity<?> updateOne(@PathVariable int id, @RequestBody T updateValueEntity) {
		T updatebleEntity = entityService.getById(id);
		if (updatebleEntity == null) {
			return ResponseEntity.notFound().build();
		}

		try {
			return ResponseEntity.ok(assembler.toResource(entityService.update(updatebleEntity, updateValueEntity)));
		} catch (AlreadyExistsException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(new Resource<>(e.getMessage()));
		} catch (ValidationException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.badRequest().body(new Resource<>(e.getMessage()));
		}
	}

	protected Resources<Resource<T>> toPageableResource(List<T> entities, Page page, Map<String, String> params) {
		Class<? extends AbstractController<T>> controller = getControllerClass();

		List<Resource<T>> resources = new LinkedList<>();
		entities.forEach(e -> resources.add(assembler.toResource(e)));

		Resources<Resource<T>> resourcesBath = new Resources<Resource<T>>(resources);

		if (page.getSize() != -1) {
			params.put("page", "1");
			params.put("size", String.valueOf(page.getSize()));

			resourcesBath.add(linkTo(methodOn(controller).getAll(params)).withSelfRel());

			if (page.getNumber() != page.first()) {

				params.put("page", String.valueOf(page.first()));
				resourcesBath.add(linkTo(methodOn(controller).getAll(params)).withRel("first"));
				
				if (page.prev() != page.first()) {
					params.put("page", String.valueOf(page.prev()));
					resourcesBath.add(linkTo(methodOn(controller).getAll(params)).withRel("prev"));
				}
			}
			
			if (page.getNumber() != page.last()) {
				if (page.next() != page.last()) {
					params.put("page", String.valueOf(page.next()));
					resourcesBath.add(linkTo(methodOn(controller).getAll(params)).withRel("next"));
				}
				params.put("page", String.valueOf(page.last()));
				resourcesBath.add(linkTo(methodOn(controller).getAll(params)).withRel("last"));
			}
		} else {
			resourcesBath.add(linkTo(methodOn(controller).getAll(params)).withSelfRel());
		}

		return resourcesBath;
	}

	protected abstract Class<? extends AbstractController<T>> getControllerClass();

}
