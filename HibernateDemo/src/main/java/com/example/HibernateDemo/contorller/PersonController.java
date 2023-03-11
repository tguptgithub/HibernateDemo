package com.example.HibernateDemo.contorller;


import com.example.HibernateDemo.dto.CreatePersonDTO;
import com.example.HibernateDemo.model.Person;
import com.example.HibernateDemo.service.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PersonController {
    private static Logger logger= LoggerFactory.getLogger(PersonController.class);
    @Autowired
    PersonServiceImpl personservice;

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody @Valid CreatePersonDTO createPersonDto){
        personservice.createPersonStatic(createPersonDto);
       // personservice.createPersonStatic(createPersonDto);
        return new ResponseEntity("Person object is created", HttpStatus.CREATED);
    }

    @GetMapping("/person")
    public ResponseEntity<Person> getPersonById(@RequestParam("id") int id){
         return new ResponseEntity(personservice.getPersonById(id), HttpStatus.OK);
    }

    @GetMapping("/person/list")
    public ResponseEntity<Person> getAllPerson(){
        return new ResponseEntity(personservice.getAllPersons(), HttpStatus.OK);
    }

    @DeleteMapping("/person/list/{id}")
    public ResponseEntity<Person> deletePersonById(@PathVariable int id){
        personservice.deletePersonById(id);
        return new ResponseEntity(id+ " deleted successfully ", HttpStatus.OK);
    }
}
