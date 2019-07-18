package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Task;

@Repository
public class TaskDao extends AbstractDao<Task> {

	@Override
	public Class<Task> getEntityClass() {
		return Task.class;
	}

	@Override
	public String getEntityName() {
		return Task.class.getName();
	}

}
