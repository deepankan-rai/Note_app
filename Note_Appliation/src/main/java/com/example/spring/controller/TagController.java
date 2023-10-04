package com.example.spring.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.spring.config.TagService;
import com.example.spring.entity.Notes;
import com.example.spring.entity.UserDtls;
import com.example.spring.repository.NotesRepository;
import com.example.spring.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/notes/tags")
public class TagController {
    
    @Autowired
	private UserRepository userRepository;
	
    @Autowired
    private TagService tagService;
    
    @Autowired
    private NotesRepository notesRepository;


	@ModelAttribute
	public void addCommnData(Principal p, Model m) {
		String email = p.getName();
		UserDtls user = userRepository.findByEmail(email);
		m.addAttribute("user", user);
	}
	
	@PostMapping("/addNotes")
	public String home() {
		return "user/add_notes";
	}

	
	@PutMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model m) {

		Optional<Notes> n = notesRepository.findById(id);
		if (n != null) {
			Notes notes = n.get();
			m.addAttribute("notes", notes);
		}

		return "user/edit_notes";
	}
	
	@PutMapping("/updateNotes")
	public String updateNotes(@ModelAttribute Notes notes, HttpSession session, Principal p) {
		String email = p.getName();
		UserDtls user = userRepository.findByEmail(email);

		notes.setUserDtls(user);

		Notes updateNotes = notesRepository.save(notes);

		if (updateNotes != null) {
			session.setAttribute("msg", "Notes Update Sucessfully");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}

		System.out.println(notes);

		return "redirect:/user/viewNotes/0";
	}



}
