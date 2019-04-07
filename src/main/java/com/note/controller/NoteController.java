package com.note.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.note.model.Note;
import com.note.model.User;
import com.note.service.NoteService;
import com.note.service.UserService;
 
@Controller
@RequestMapping("/NoteApp")
public class NoteController {
 
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String login(ModelMap model) { 
		
		User user=new User();
		model.addAttribute("user",user);

		return "index";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String doLogin(ModelMap model,@ModelAttribute User user,HttpSession session) { 
		
		User user1=userService.findUser(user.getName());
		
		if(user1==null) {
			
			model.addAttribute("user",user);
			model.addAttribute("msg","Invalid Credentials");
			return "index";
		}
		session.setAttribute("userId", user.getName());
		List<Note> notes=noteService.fetchNotesOfUser(user.getName());
		
		model.addAttribute("notes",notes);
		model.addAttribute("username",user1.getName());

		return "viewNote";
	}
 
	@RequestMapping(value="/saveNote", method = RequestMethod.POST)
	public String register(ModelMap model,@ModelAttribute Note note) { 
		
		LocalDateTime date=LocalDateTime.now();
		if(note.getId()>0) 
		note.setCrtDate(date);
		else
		note.setMdyDate(date);
	
		
		noteService.saveNotes(note);
		model.addAttribute("successMessage", "Note Saved: Success!");

		
		List<Note> notes=noteService.fetchNotesOfUser(note.getCrtBy());
		
		model.addAttribute("notes",notes);
		return "viewNote";
 
	}
	
	@GetMapping(value="/userAccount")
	public String userRegister(ModelMap model) { 
		
		User user=new User();
		model.addAttribute("user", user);

		return "createUser";
 
	}
	
	@PostMapping(value="/createNote/{name}")
	public String register(ModelMap model,@PathVariable("name") String name) { 
		Note note=new Note();
		model.addAttribute("msgArgument", "Go to registration!");
		note.setCrtBy(name);
		model.addAttribute("note", note);

		return "createNote";
	}
	
	@RequestMapping(value="/view", method = RequestMethod.POST)
	public String viewNote(ModelMap model,@ModelAttribute Note note,@RequestParam long id) { 
		note=noteService.findNote(id);
		model.addAttribute("note", note);

		return "viewNote";
	}
	
	@PostMapping(value="/createUser")
	public String registerUser(ModelMap model,@ModelAttribute User user) { 
		
		userService.saveUser(user);
		model.addAttribute("successMessage", "User account created: Success!");

		return "createUser";
 
	}
	
	@GetMapping(value="/edit/{id}")
	public String editNote(ModelMap model,@PathVariable("id") long id) { 
		Note note=noteService.findNote(id);
		model.addAttribute("note", note);

		return "createNote";
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteNote(ModelMap model,@PathVariable("id") long id,HttpSession session) { 
		noteService.deleteNote(id);

		List<Note> notes=noteService.fetchNotesOfUser(session.getAttribute("userId").toString());
		
		model.addAttribute("notes",notes);
		return "viewNote";
	}
	
	
}
