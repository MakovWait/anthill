package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.UserProfile;

@Repository
public class UserProfileDao extends AbstractDao<UserProfile> {

	@Override
	public Class<UserProfile> getEntityClass() {
		return UserProfile.class;
	}

	@Override
	public String getEntityName() {
		return UserProfile.class.getName();
	}

}
