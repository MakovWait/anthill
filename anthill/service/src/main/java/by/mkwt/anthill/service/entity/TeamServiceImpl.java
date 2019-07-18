package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.JoinType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.util.TeamService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class TeamServiceImpl extends AbstractService<Team> implements TeamService {

	@Autowired
	public TeamServiceImpl(EntityManagerFactory entityManagerFactory, AbstractDao<Team> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}

	@Override
	protected void updateEntityLogic(Team entity, Team with) {
		entity.setName(with.getName());
	}

	@Override
	public Team getTeamWithMembers(int teamId) {
		Team team = fetchWith(teamId, "members", JoinType.LEFT);
		return team;
	}

}
