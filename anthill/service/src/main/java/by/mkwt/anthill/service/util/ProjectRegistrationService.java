package by.mkwt.anthill.service.util;

import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.exception.RegistrationException;

public interface ProjectRegistrationService {
	
	Project registerProject(Project project, Team team, User owner) throws RegistrationException;
	
}
