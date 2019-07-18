package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Team;

@Repository
public class TeamDao extends AbstractDao<Team> {

	@Override
	public Class<Team> getEntityClass() {
		return Team.class;
	}

	@Override
	public String getEntityName() {
		return Team.class.getName();
	}

}
