package com.example.spring.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring.entity.Notes;
import com.example.spring.entity.Tag;
import com.example.spring.repository.NotesRepository;
import com.example.spring.repository.TagRepository;

public class TagService {
	
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private TagRepository tagRepository;

	
	  public void addTagToNote(Integer id, String tagName) {
	        Notes note = notesRepository.findById(id)
	                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
	        
	        Tag tag = tagRepository.findByTagName(tagName);
	        if (tag == null) {
	            tag = new Tag(tagName);
	        }

	        note.addTag(tag);
	        notesRepository.save(note);
	    }
	  
	  public void updateNoteWithTags(Integer id,Set<String> tagNames) {
		  Notes note=notesRepository.findById(id).orElseThrow(()-> new NoteNotFoundException("Note not found with id:"+id));
		  
		  Set<Tag> tags=new HashSet<>();
		 
		  for (String tagName : tagNames) {
	            Tag tag = tagRepository.findByTagName(tagName);
	            if (tag == null) {
	                tag = new Tag(tagName);
	            }
	            tags.add(tag);
	        }
		  note.setTags(tags);
		  
		  notesRepository.save(note);
	  }

}
