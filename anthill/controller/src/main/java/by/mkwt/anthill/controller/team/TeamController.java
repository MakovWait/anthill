package by.mkwt.anthill.controller.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.mkwt.anthill.controller.AbstractController;
import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.util.TeamService;

@RestController
@RequestMapping(value = "/teams")
public class TeamController extends AbstractController<Team> {

	private TeamService teamService;

	@Autowired
	public TeamController(TeamService entityService, ResourceAssembler<Team, Resource<Team>> assembler) {
		super((AbstractService<Team>) entityService, assembler);
		teamService = entityService;
	}

	@Override
	public ResponseEntity<?> getOne(@PathVariable int id) {
		Team team = teamService.getTeamWithMembers(id);

		if (team != null) {
			TeamResource teamResource = new TeamResource(team.getName(), team.getMembers());
			return ResponseEntity.ok(teamResource);
		} else {
			return ResponseEntity.notFound().build();
		}		
	}

	@Override
	protected Class<? extends AbstractController<Team>> getControllerClass() {
		return this.getClass();
	}

}
