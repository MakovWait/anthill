package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.Skill;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.validation.ValidationHandler;

@Service
public class SkillService extends AbstractService<Skill>{

	@Autowired
	public SkillService(EntityManagerFactory entityManagerFactory, AbstractDao<Skill> entityDao, ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}

	@Override
	protected void updateEntityLogic(Skill entity, Skill with) {
		entity.setName(with.getName());
	}
	
}
