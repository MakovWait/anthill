package by.mkwt.anthill.controller.note;

import by.mkwt.anthill.entity.project.Note;
import by.mkwt.anthill.entity.project.Project;

public class NoteForm {

	private Note note;
	private Integer projectId;

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
