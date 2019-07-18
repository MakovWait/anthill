package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.Skill;

@Repository
public class SkillDao extends AbstractDao<Skill> {

	@Override
	public Class<Skill> getEntityClass() {
		return Skill.class;
	}

	@Override
	public String getEntityName() {
		return getEntityClass().getName();
	}

}
