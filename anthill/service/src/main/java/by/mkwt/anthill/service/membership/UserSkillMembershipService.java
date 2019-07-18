package by.mkwt.anthill.service.membership;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.entity.user.Skill;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractPairService;
import by.mkwt.anthill.service.AbstractService;

@Service
public class UserSkillMembershipService extends AbstractPairService<User, Skill> {

	@Autowired
	public UserSkillMembershipService(EntityManagerFactory entityManagerFactory, AbstractService<User> ownerService,
			AbstractService<Skill> ownedService) {
		super(entityManagerFactory, ownerService, ownedService, "skills");
	}

	@Override
	protected void addLogic(User owner, Skill owned) {
		owner.getSkills().add(owned);
	}

	@Override
	protected void removeLogic(User owner, Skill owned) {
		owner.getSkills().remove(owned);
	}

	@Override
	protected void allOwnedByOwnerFilterLogic(Integer ownerId, Map<String, String> filter) {
		filter.put("filter[user_id][eq]", String.valueOf(ownerId));
	}

	@Override
	protected void clearFilterLogic(Map<String, String> filter) {
		if (filter.containsKey("filter[user_id][eq]")) {
			filter.remove("filter[user_id][eq]");
		}
	}

}
