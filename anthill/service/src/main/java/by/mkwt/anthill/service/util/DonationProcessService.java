package by.mkwt.anthill.service.util;

import by.mkwt.anthill.entity.user.Donation;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.service.exception.DonationException;
import by.mkwt.anthill.validation.exception.ValidationException;

public interface DonationProcessService {

	Donation handleDonation(Donation donation) throws ValidationException, AlreadyExistsException, DonationException;
	
}
