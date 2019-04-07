package com.note.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.model.Note;
import com.note.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepo;
	
	public void saveNotes(Note note) {
       
		noteRepo.save(note);
    }
	
	public Note findNote(long id) {
		
		return noteRepo.findById(id).get();
		
	}
	
	public List<Note> fetchNotesOfUser(String crtBy){
		List<Note> notes=noteRepo.findAllByCrtBy(crtBy);
		if(notes.size()>0)
		return notes;
		else
			return null;
	}
	
	public void deleteNote(long id) {
		 noteRepo.deleteById(id);		
	}
}
