package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Project;

@Repository
public class ProjectDao extends AbstractDao<Project> {

	@Override
	public Class<Project> getEntityClass() {
		return Project.class;
	}

	@Override
	public String getEntityName() {
		return Project.class.getName();
	}

}
