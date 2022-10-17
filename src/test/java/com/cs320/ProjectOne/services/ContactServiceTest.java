package com.cs320.ProjectOne.services;

import com.cs320.ProjectOne.models.Contact;
import com.cs320.ProjectOne.repositories.ContactRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.validation.ConstraintViolationException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepo contactRepo;
    @InjectMocks
    private ContactService contactService;

    private Contact contact;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        contactService = new ContactService(contactRepo);
        contact = new Contact(1, "Eric", "Florence", "7187179415", "123 Colony Blvd");
    }

    @Test
    void createContact() {
        given(contactRepo.save(this.contact)).willReturn(contact);
        Contact contact1 = contactService.createContact(this.contact);
        assertEquals(contact1, this.contact);
        assertThat(contact1).isNotNull();
    }

    @Test
    public void givenContactId_whenDeleteContact_thenNothing() {
        willDoNothing().given(contactRepo).deleteById(contact.getId());
        contactService.deleteContactById(contact.getId());
        verify(contactRepo, times(1)).deleteById(contact.getId());
    }

    @Test
    @Sql("/Users/m_2171923/Desktop/SNHU/CS-320/ProjectOne/src/main/resources/data.sql")
    void givenContactObject_whenUpdateContact_thenReturnUpdatedContact() {
        contact = contactService.getContactById(1);
        given(contactRepo.save(contact)).willReturn(contact);
        contact.setFirstName("Bob");
        contact.setLastName("Saget");
        Contact updatedContact = contactService.updateContactById(1, contact);
        assertThat(updatedContact.getFirstName()).isEqualTo("Bob");
        assertThat(updatedContact.getLastName()).isEqualTo("Saget");
    }

    @Test
    void first_name_input_meets_character_constraint() {

        Contact newContact = new Contact(2, "ERICERICERIC", "Florence", "7187179415", "123 Colony Blvd");

        if(newContact.getFirstName().length() > 10) {
            System.out.println("First Name is longer than 10 characters, please enter shorter name");
            when(contactRepo.save(newContact)).thenThrow(ConstraintViolationException.class);
            assertThrows(ConstraintViolationException.class, () -> contactService.createContact(newContact));
        }

        else {
            when(contactRepo.save(any())).thenReturn(newContact);
            Contact newContact1 = contactService.createContact(newContact);
            assertEquals(newContact1, newContact);
        }

    }

    @Test
    void last_name_input_meets_character_constraint() {

        Contact newContact = new Contact(2, "Eric", "FlorenceFlorence", "7187179415", "123 Colony Blvd");

        if(newContact.getLastName().length() > 10) {
            System.out.println("Last Name is longer than 10 characters, please enter shorter name");
            when(contactRepo.save(newContact)).thenThrow(ConstraintViolationException.class);
            assertThrows(ConstraintViolationException.class, () -> contactService.createContact(newContact));
        }

        else {
            when(contactRepo.save(any())).thenReturn(newContact);
            Contact newContact1 = contactService.createContact(newContact);
            assertEquals(newContact1, newContact);
        }

    }

    @Test
    void phone_number_input_meets_character_constraint() {

        Contact newContact = new Contact(2, "Eric", "Florence", "718717941", "123 Colony Blvd");

        if(newContact.getPhoneNumber().length() != 10) {
            System.out.println("Phone number must be 10 digits, please re-enter your correct number");
            when(contactRepo.save(newContact)).thenThrow(ConstraintViolationException.class);
            assertThrows(ConstraintViolationException.class, () -> contactService.createContact(newContact));
        }

        else {
            when(contactRepo.save(any())).thenReturn(newContact);
            Contact newContact1 = contactService.createContact(newContact);
            assertEquals(newContact1, newContact);
        }

    }

    @Test
    void address_input_meets_character_constraint() {

        Contact newContact = new Contact(2, "Eric", "Florence", "7187179415", "123 Colony Blvd 123 Colony Blvd 123 Colony Blvd");

        if(newContact.getAddress().length() > 30) {
            System.out.println("Address can't be longer than 30 characters, please re-enter a valid address");
            when(contactRepo.save(newContact)).thenThrow(ConstraintViolationException.class);
            assertThrows(ConstraintViolationException.class, () -> contactService.createContact(newContact));
        }

        else {
            when(contactRepo.save(any())).thenReturn(newContact);
            Contact newContact1 = contactService.createContact(newContact);
            assertEquals(newContact1, newContact);
        }

    }
}