package by.mkwt.anthill.service.membership;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.entity.project.Team;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractPairService;
import by.mkwt.anthill.service.AbstractService;

@Service
public class TeamUserMembershipService extends AbstractPairService<Team, User>{

	@Autowired
	public TeamUserMembershipService(EntityManagerFactory entityManagerFactory, AbstractService<Team> ownerService,
			AbstractService<User> ownedService) {
		super(entityManagerFactory, ownerService, ownedService, "members");
	}

	@Override
	protected void addLogic(Team owner, User owned) {
		owner.getMembers().add(owned);
	}

	@Override
	protected void removeLogic(Team owner, User owned) {		
		owner.getMembers().remove(owned);
	}

	@Override
	protected void allOwnedByOwnerFilterLogic(Integer ownerId, Map<String, String> filter) {
		filter.put("filter[team_id][eq]", String.valueOf(ownerId));
	}

	@Override
	protected void clearFilterLogic(Map<String, String> filter) {
		if (filter.containsKey("filter[team_id][eq]")) {
			filter.remove("filter[team_id][eq]");
		}
	}

}
