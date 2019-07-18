package by.mkwt.anthill.service.membership;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.entity.user.User;
import by.mkwt.anthill.service.AbstractPairService;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.exception.ValidationException;

@Service
public class OwnerProjectsMembershipService extends AbstractPairService<User, Project>{
	
	@Autowired
	public OwnerProjectsMembershipService(EntityManagerFactory entityManagerFactory, AbstractService<User> ownerService,
			AbstractService<Project> ownedService) {
		super(entityManagerFactory, ownerService, ownedService, "projects");
	}

	@Override
	protected void addLogic(User owner, Project owned) {
		owner.getProjects().add(owned);
	}

	@Override
	protected void removeLogic(User owner, Project owned) {
		owner.getProjects().remove(owned);
	}

	@Override
	protected void allOwnedByOwnerFilterLogic(Integer ownerId, Map<String, String> filter) {		
		filter.put("filter[owner_id][eq]", String.valueOf(ownerId));
	}

	@Override
	protected void clearFilterLogic(Map<String, String> filter) {
		if (filter.containsKey("filter[owner_id][eq]")) {
			filter.remove("filter[owner_id][eq]");
		}
	}

}
