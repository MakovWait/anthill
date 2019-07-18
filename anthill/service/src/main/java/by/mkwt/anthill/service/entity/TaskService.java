package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Task;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class TaskService extends AbstractService<Task> {

	@Autowired
	public TaskService(EntityManagerFactory entityManagerFactory, AbstractDao<Task> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}

	@Override
	protected void updateEntityLogic(Task entity, Task with) {
		entity.setTitle(with.getTitle());
		entity.setFinished(with.isFinished());
		entity.setDescription(with.getDescription());
	}	
	
}
