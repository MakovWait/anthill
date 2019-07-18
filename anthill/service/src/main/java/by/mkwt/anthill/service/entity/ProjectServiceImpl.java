package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Genre;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class ProjectServiceImpl extends AbstractService<Project> {
	
	private AbstractDao<Genre> genreDao;
	
	@Autowired
	public ProjectServiceImpl(EntityManagerFactory entityManagerFactory, AbstractDao<Project> entityDao, AbstractDao<Genre> genreDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
		this.genreDao = genreDao;
	}

	@Override
	protected void updateEntityLogic(Project entity, Project with) {
		entity.setName(with.getName());
		entity.setDescription(with.getDescription());
		entity.setGoalCash(with.getGoalCash());
		entity.setArchived(with.isArchived());
	}

}
