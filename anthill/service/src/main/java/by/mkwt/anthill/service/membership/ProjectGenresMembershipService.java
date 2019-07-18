package by.mkwt.anthill.service.membership;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.entity.project.Genre;
import by.mkwt.anthill.entity.project.Project;
import by.mkwt.anthill.service.AbstractPairService;
import by.mkwt.anthill.service.AbstractService;

@Service
public class ProjectGenresMembershipService extends AbstractPairService<Project, Genre> {

	@Autowired
	public ProjectGenresMembershipService(EntityManagerFactory entityManagerFactory, AbstractService<Project> ownerService,
			AbstractService<Genre> ownedService) {
		super(entityManagerFactory, ownerService, ownedService, "genres");
	}

	@Override
	public void addLogic(Project owner, Genre owned) {
		owner.getGenres().add(owned);
	}

	@Override
	public void removeLogic(Project owner, Genre owned) {
		owner.getGenres().remove(owned);
	}

	@Override
	protected void allOwnedByOwnerFilterLogic(Integer ownerId, Map<String, String> filter) {
		filter.put("filter[project_id][eq]", String.valueOf(ownerId));
	}

	@Override
	protected void clearFilterLogic(Map<String, String> filter) {
		if (filter.containsKey("filter[project_id][eq]")) {
			filter.remove("filter[project_id][eq]");
		}
	}

}
