package by.mkwt.anthill.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import by.mkwt.anthill.dao.entity.BenefitDao;
import by.mkwt.anthill.dao.entity.DonationDao;
import by.mkwt.anthill.dao.entity.GenreDao;
import by.mkwt.anthill.dao.entity.LoginCredentialsDao;
import by.mkwt.anthill.dao.entity.NoteDao;
import by.mkwt.anthill.dao.entity.ProjectDao;
import by.mkwt.anthill.dao.entity.RequestDao;
import by.mkwt.anthill.dao.entity.SkillDao;
import by.mkwt.anthill.dao.entity.TaskDao;
import by.mkwt.anthill.dao.entity.UserDao;
import by.mkwt.anthill.dao.util.HibernateEntityManagerFactory;
import by.mkwt.anthill.entity.project.Benefit;
import by.mkwt.anthill.entity.project.Genre;
import by.mkwt.anthill.entity.project.Note;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.project.Request;
import by.mkwt.anthill.entity.project.Task;
import by.mkwt.anthill.entity.user.Donation;
import by.mkwt.anthill.entity.user.LoginCredentials;
import by.mkwt.anthill.entity.user.Skill;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.entity.BenefitService;
import by.mkwt.anthill.service.entity.DonationService;
import by.mkwt.anthill.service.entity.GenreServiceImpl;
import by.mkwt.anthill.service.entity.LoginCredentialsService;
import by.mkwt.anthill.service.entity.NoteService;
import by.mkwt.anthill.service.entity.ProjectServiceImpl;
import by.mkwt.anthill.service.entity.RequestService;
import by.mkwt.anthill.service.entity.SkillService;
import by.mkwt.anthill.service.entity.TaskService;
import by.mkwt.anthill.service.entity.UserService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.ValidationHandler;
import by.mkwt.anthill.validation.exception.ValidationException;

public class App {

	public static void main(String[] args) {
		ValidationHandler validationHandler = new ValidationHandler();
		AbstractService<Skill> skillService = new SkillService(HibernateEntityManagerFactory.FACTORY, new SkillDao(),
				validationHandler);
		
		AbstractService<User> userService = new UserService(HibernateEntityManagerFactory.FACTORY, new UserDao(),
				validationHandler);
		
//		AbstractService<LoginCredentials> loginCredentialsService = new LoginCredentialsService(HibernateEntityManagerFactory.FACTORY, new LoginCredentialsDao(),
//				validationHandler);
		
//		AbstractService<Project> projectService = new ProjectServiceImpl(HibernateEntityManagerFactory.FACTORY, new ProjectDao(),
//				validationHandler);
		
		AbstractService<Request> requestService = new RequestService(HibernateEntityManagerFactory.FACTORY, new RequestDao(),
				validationHandler);
		
		AbstractService<Note> noteService = new NoteService(HibernateEntityManagerFactory.FACTORY, new NoteDao(),
				validationHandler);
		
		AbstractService<Task> taskService = new TaskService(HibernateEntityManagerFactory.FACTORY, new TaskDao(),
				validationHandler);
		
		AbstractService<Benefit> benefitService = new BenefitService(HibernateEntityManagerFactory.FACTORY, new BenefitDao(),
				validationHandler);
		
		AbstractService<Donation> donationService = new DonationService(HibernateEntityManagerFactory.FACTORY, new DonationDao(),
				validationHandler);
		
//		AbstractService<Genre> genreService = new GenreServiceImpl(HibernateEntityManagerFactory.FACTORY, new GenreDao(),
//				validationHandler);
		
		
		try {
//			EntityManager entityManager = HibernateEntityManagerFactory.FACTORY.createEntityManager();
//			entityManager.persist(user);
//			user.getSkills().add(skillService.getById(1));
//			entityManager.close();
			
			//System.out.println(genreService.getAll());
			
//			try {
//				projectService.add(new Project(")))", "kvrnk", userService.getById(2)));
//			} catch (AlreadyExistsException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ValidationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
//			GenreDao genreDao = new GenreDao();
//			EntityManager em = HibernateEntityManagerFactory.FACTORY.createEntityManager();
//			EntityTransaction tx = em.getTransaction();
//			System.out.println(genreDao.genres(em, 3).getProjects());
//			tx.begin();
//			Genre genre = genreDao.genres(em, 1).get(1);
//			System.out.println(genre.getProjects());
//			genre.getProjects().remove(projectService.getById(2));
//			System.out.println(genre.getProjects());			
//			tx.commit();
//			em.close();
////			genreService.update(genre);
//			
//			em = HibernateEntityManagerFactory.FACTORY.createEntityManager();
//			tx = em.getTransaction();
//			tx.begin();
//			System.out.println(genreDao.genres(em, 1).get(0).getProjects());
//			tx.commit();
//			em.close();
			
		} finally {
			HibernateEntityManagerFactory.FACTORY.close();
		}

	}

}
