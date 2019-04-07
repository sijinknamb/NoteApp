package com.note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.note.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	
    List<Note> findAllByCrtBy(String crtBy);
}