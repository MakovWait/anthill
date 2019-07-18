package by.mkwt.anthill.service.entity;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Note;
import by.mkwt.anthill.service.AbstractService;
import by.mkwt.anthill.service.exception.AlreadyExistsException;
import by.mkwt.anthill.validation.ValidationHandler;
import by.mkwt.anthill.validation.exception.ValidationException;

@Service
public class NoteService extends AbstractService<Note> {

	@Autowired
	public NoteService(EntityManagerFactory entityManagerFactory, AbstractDao<Note> entityDao,
			ValidationHandler validationHandler) {
		super(entityManagerFactory, entityDao, validationHandler);
	}
	
	@Override
	public Note add(Note entity) throws AlreadyExistsException, ValidationException {
		// TODO Auto-generated method stub
		return super.add(entity);
	}

	@Override
	protected void updateEntityLogic(Note entity, Note with) {
		entity.setContent(with.getContent());
		entity.setTitle(with.getTitle());
	}

}
