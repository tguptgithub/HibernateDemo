package com.example.HibernateDemo.repository;

import com.example.HibernateDemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepositoryInterf extends JpaRepository<Person, Integer> {
}
