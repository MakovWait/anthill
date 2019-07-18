package by.mkwt.anthill.service.util;

import by.mkwt.anthill.entity.project.Team;

public interface TeamService {

	Team getTeamWithMembers(int teamId);
	
}
