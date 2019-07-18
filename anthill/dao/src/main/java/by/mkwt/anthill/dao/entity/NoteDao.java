package by.mkwt.anthill.dao.entity;

import org.springframework.stereotype.Repository;

import by.mkwt.anthill.dao.AbstractDao;
import by.mkwt.anthill.entity.project.Note;

@Repository
public class NoteDao extends AbstractDao<Note> {

	@Override
	public Class<Note> getEntityClass() {
		return Note.class;
	}

	@Override
	public String getEntityName() {
		return Note.class.getName();
	}

}
