package by.mkwt.anthill.service.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.service.exception.RegistrationException;
import by.mkwt.anthill.validation.exception.ValidationException;

@Service
public class ProjectRegistrationServiceImpl implements ProjectRegistrationService {

	private EntityManagerFactory entityManagerFactory;
	private AbstractService<Project> projectService;
	private AbstractService<Team> teamService;
	private AbstractService<User> userService;

	@Autowired
	public ProjectRegistrationServiceImpl(EntityManagerFactory entityManagerFactory,
			AbstractService<Project> projectService, AbstractService<Team> teamService, AbstractService<User> userService) {
				this.entityManagerFactory = entityManagerFactory;
				this.projectService = projectService;
				this.teamService = teamService;
				this.userService = userService;
	}

	@Override
	public Project registerProject(Project project, Team team, User owner) throws RegistrationException {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		try {
			Team persistedTeam = teamService.addWithoutCommit(em, team);
			persistedTeam.getMembers().add(owner);
			project.setTeam(persistedTeam);
			project.setOwner(owner);
			projectService.addWithoutCommit(em, project);
			em.getTransaction().commit();
		} catch (ValidationException | AlreadyExistsException e) {
			em.getTransaction().rollback();
			throw new RegistrationException(e.getMessage());
		} finally {
			em.close();
		}
		
		return project;
	}

}
