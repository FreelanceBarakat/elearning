package com.ldap.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ldap.repository.GroupRepository;
import com.ldap.repository.PersonRepository;
import com.ldap.repository.models.Person;
import com.student.service.responses.GenericResponse;

@RestController
public class HomeController {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Value("${spring.ldap.embedded.ldif}")
	String ldifPath;
	

	@RequestMapping(value = "signup", method = RequestMethod.POST, consumes = "application/JSON", produces = "application/JSON")
	public GenericResponse signup(@Valid @RequestBody Person person) throws IOException, URISyntaxException {

		log.info("Atempting signup with {}", person);
		personRepository.create(person);

		return new GenericResponse(200, "Successful");
	}

	@RequestMapping(value = "viewAllUsers", method = RequestMethod.POST, consumes = "application/JSON", produces = "application/JSON")
	public List<Person> managers() {
		System.out.println("\n"+ldifPath + "\n");
		return personRepository.findAll();
	}

	@GetMapping("/employees")
	public String employees() {
		return "Hello employees";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<?> handleRuntimeExceptions(RuntimeException ex) {
	  // log it
	  return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleExceptions(Exception ex) {
	  // log it
	  return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}