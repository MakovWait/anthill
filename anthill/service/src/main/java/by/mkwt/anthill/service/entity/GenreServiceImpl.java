package by.mkwt.anthill.service.entity;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Genre;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.ValidationHandler;
import by.mkwt.anthill.validation.exception.ValidationException;

@Service
public class GenreServiceImpl extends AbstractService<Genre> {
	
	private AbstractDao<Project> projectDao;
	
	@Autowired
	public GenreServiceImpl(EntityManagerFactory entityManagerFactory, AbstractDao<Genre> entityDao,
			ValidationHandler validationHandler, AbstractDao<Project> projectDao) {
		super(entityManagerFactory, entityDao, validationHandler);
		this.projectDao = projectDao;
	}

	@Override
	@Cacheable(cacheNames = "genres")
	public List<Genre> getAll() {
		return super.getAll();
	}
	
	@Override
	@CacheEvict(cacheNames="genres", allEntries = true)
	public Genre add(Genre entity) throws AlreadyExistsException, ValidationException {
		return super.add(entity);
	}
	
//	@Override
//	public Set<Project> getGenreProjects(int genreId, Page page) {
//		Genre genre = getById(genreId);
//		EntityManager em = getEntityManager();
//		
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("filter[genre_id][eq]", String.valueOf(genre.getId()));
//		
//		Set<Project> projects = null;
//		
//		try {
//			projects = new HashSet<Project>(projectDao.readAll(em, page, params));
//		} catch (FilterException e) {
//			e.printStackTrace();
//		}
//		
//		genre.setProjects(projects);
//
//		em.close();
//		
//		return projects;
////		return fetchWith(genreId, "projects", JoinType.INNER);
//	}

	@Override
	protected void updateEntityLogic(Genre entity, Genre with) {
		entity.setName(with.getName());
	}
	
}
