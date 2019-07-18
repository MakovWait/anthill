package by.mkwt.anthill.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.sun.org.apache.bcel.internal.generic.Select;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.dao.entity.ProjectDao;
import by.mkwt.anthill.dao.entity.TeamDao;
import by.mkwt.anthill.dao.util.HibernateEntityManagerFactory;
import by.mkwt.anthill.dao.util.Page;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.filter.Filter;
import by.mkwt.anthill.filter.FilterQueryParser;
import by.mkwt.anthill.filter.exception.FilterException;
import by.mkwt.anthill.service.entity.TeamServiceImpl;
import by.mkwt.anthill.validation.ValidationHandler;

public class App {
	
	public static void main(String[] args) {
//		ValidationHandler validationHandler = new ValidationHandler();
//		AbstractService<Skill> skillService = new SkillService(HibernateEntityManagerFactory.FACTORY, new SkillDao(),
//				validationHandler);
//
//		AbstractService<User> userService = new UserService(HibernateEntityManagerFactory.FACTORY, new UserDao(),
//				validationHandler);
//
//		AbstractService<LoginCredentials> loginCredentialsService = new LoginCredentialsService(
//				HibernateEntityManagerFactory.FACTORY, new LoginCredentialsDao(), validationHandler);
//
//		
//		ProjectService projectService = new ProjectServiceImpl(HibernateEntityManagerFactory.FACTORY, new ProjectDao(),
//				validationHandler);
//
//		AbstractService<Request> requestService = new RequestService(HibernateEntityManagerFactory.FACTORY,
//				new RequestDao(), validationHandler);
//
//		AbstractService<Note> noteService = new NoteService(HibernateEntityManagerFactory.FACTORY, new NoteDao(),
//				validationHandler);
//
//		AbstractService<Task> taskService = new TaskService(HibernateEntityManagerFactory.FACTORY, new TaskDao(),
//				validationHandler);
//
//		AbstractService<Benefit> benefitService = new BenefitService(HibernateEntityManagerFactory.FACTORY,
//				new BenefitDao(), validationHandler);
//
//		AbstractService<Donation> donationService = new DonationService(HibernateEntityManagerFactory.FACTORY,
//				new DonationDao(), validationHandler);
//
//		GenreServiceImpl genreService = new GenreServiceImpl(HibernateEntityManagerFactory.FACTORY, new GenreDao(),
//				validationHandler);
//
//		
//		
//		//GenreController genreController = new GenreController(genreService, projectService);
//
//		ApplicationContext context =
//				new AnnotationConfigApplicationContext("by.mkwt.anthill");
//		
//		GenreController genreController = context.getBean(GenreController.class);
//		
		try {
			// System.out.println(genreController.getProjectsByGenre(3, new Page(1, 1)));

			
			EntityManager em = HibernateEntityManagerFactory.FACTORY.createEntityManager();
			AbstractDao<Team> teamDao = new TeamDao();
			
			//Team team = teamDao.readOne(em, 6);

			TeamServiceImpl teamService = new TeamServiceImpl(HibernateEntityManagerFactory.FACTORY, teamDao, new ValidationHandler());
			//Team team = teamService.fetchWith(6, "members", JoinType.INNER);
			
			//em.close();
			
//			System.out.println(em.find(T.class, 6).getMembers());
			
			//			Filter<Project> filter = new Filter<Project>();
//			FilterQueryParser parser = new FilterQueryParser();
////			
//			Map<String, String> params = new HashMap<String, String>();
////
//			params.put("owner_name[eq][0]", "kvrnk");
//			params.put("owner_name[eq][1]", "Makovka");
////			params.put("owner[eq][1]", "Makovka");
//			
//			//params.put("createDate[gt][0]", "2019-06-28T00:00:00.0");
////			params.put("createDate[lt][1]", "2019-06-27T14:00:00.0");
////			params.put("goalCash[gt][1]", "0.1");
//			
////			params.put("isArchived[eq]", "true");
//			
////			params.put("genre_name[eq][0]", "third");
////			params.put("genre_name[eq][1]", "new");
//
////			params.put("isArchived", "false");
//
////			params.put("owner[t][0]", "Makovka");
////			
//			
//			AbstractDao<Project> dao = new ProjectDao();
////			
//			try {
//				System.out.println(dao.readAll(HibernateEntityManagerFactory.FACTORY.createEntityManager(), new Page(1, 1), params));
//			} catch (FilterException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
		} finally {
			HibernateEntityManagerFactory.FACTORY.close();
		}
	}
}
