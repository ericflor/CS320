package com.cs320.ProjectOne.services;

import com.cs320.ProjectOne.models.Contact;
import com.cs320.ProjectOne.repositories.ContactRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {
    @Autowired
    private final ContactRepo contactRepo;

    public Contact createContact(Contact contact){
        return contactRepo.save(contact);
    }

    public void deleteContactById(int postId){
        contactRepo.deleteById(postId);
    }

    public Contact updateContactById(int id, Contact contact){

        Contact dbContact = getContactById(id);
        dbContact.setFirstName(contact.getFirstName());
        dbContact.setLastName(contact.getLastName());
        dbContact.setPhoneNumber(contact.getPhoneNumber());
        dbContact.setAddress(contact.getAddress());

        return contactRepo.save(dbContact);
    }

    public List<Contact> getContacts(){
        return contactRepo.findAll();
    }

    public Contact getContactById(int postId){
        return contactRepo.getReferenceById(postId);
    }
}
