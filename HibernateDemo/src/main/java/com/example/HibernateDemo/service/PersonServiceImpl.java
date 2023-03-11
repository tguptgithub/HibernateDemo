package com.example.HibernateDemo.service;

import com.example.HibernateDemo.PersonNotFoundException;
import com.example.HibernateDemo.dto.CreatePersonDTO;
import com.example.HibernateDemo.model.Person;
import com.example.HibernateDemo.repository.PersonRepositoryInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PersonServiceImpl {

    @Autowired
    PersonRepositoryInterf personRepositoryImpl;


    public void createPersonStatic(CreatePersonDTO createPersonDTO) {
        Person person= createPersonDTO.to();
        if(person.getAge() == null){
            person.setAge(calculateAgeDOB(person.getDob()));
            personRepositoryImpl.save(person);

        }

    }

    private Integer calculateAgeDOB(String dob) {
        if(dob ==null){
            return null;
        }
        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dobDate, currentDate).getYears();
    }


    public Person getPersonById(int id) {
        return personRepositoryImpl.findById(id).orElseThrow(()->
                new PersonNotFoundException("person with Id "+id+"not present"));

    }


    public void deletePersonById(int id) {
        personRepositoryImpl.deleteById(id);
    }


    public List<Person> getAllPersons() {
        return personRepositoryImpl.findAll();
    }
}
