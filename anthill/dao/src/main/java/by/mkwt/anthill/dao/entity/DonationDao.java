package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.user.Donation;

@Repository
public class DonationDao extends AbstractDao<Donation> {

	@Override
	public Class<Donation> getEntityClass() {
		return Donation.class;
	}

	@Override
	public String getEntityName() {
		return Donation.class.getName();
	}

}
