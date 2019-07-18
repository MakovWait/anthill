package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Genre;

@Repository
public class GenreDao extends AbstractDao<Genre> {
	
	@Override
	public Class<Genre> getEntityClass() {
		return Genre.class;
	}

	@Override
	public String getEntityName() {
		return Genre.class.getName();
	}

}
