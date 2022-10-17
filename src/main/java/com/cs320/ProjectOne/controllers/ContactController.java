package com.cs320.ProjectOne.controllers;

import com.cs320.ProjectOne.models.Contact;
import com.cs320.ProjectOne.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/contacts")
@RequiredArgsConstructor
public class ContactController {
    @Autowired
    private final ContactService contactService;

    @PostMapping
    public Contact createContact(@RequestBody Contact contact){
        return contactService.createContact(contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable("id") int id){
        contactService.deleteContactById(id);
    }

    @PatchMapping("/{id}")
    public Contact updateContact(@PathVariable("id") int id, @RequestBody Contact contact){
        return contactService.updateContactById(id, contact);
    }

    @GetMapping
    public List<Contact> getContacts(){
        return contactService.getContacts();
    }
}
