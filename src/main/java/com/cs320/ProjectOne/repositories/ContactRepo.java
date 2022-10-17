package com.cs320.ProjectOne.repositories;

import com.cs320.ProjectOne.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {

}
