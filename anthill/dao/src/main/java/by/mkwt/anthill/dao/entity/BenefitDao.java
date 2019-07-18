package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Benefit;

@Repository
public class BenefitDao extends AbstractDao<Benefit> {

	@Override
	public Class<Benefit> getEntityClass() {
		return Benefit.class;
	}

	@Override
	public String getEntityName() {
		return Benefit.class.getName();
	}

}
