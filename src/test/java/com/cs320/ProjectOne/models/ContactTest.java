package com.cs320.ProjectOne.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    public static final String expected_first_name = "Eric";
    public static final String expected_last_name = "Florence";
    public static final String expected_phone_number = "7187179415";
    public static final String expected_address = "123 Some Street";

    private Contact contact;

    @BeforeEach
    public void setUp() {
        contact = new Contact(1, "Eric", "Florence", "7187179415", "123 Some Street");
    }

    @Test
    public void check_first_name_values_match(){
        assertEquals(contact.getFirstName(), expected_first_name);
    }

    @Test
    public void check_last_name_values_match(){
        assertEquals(contact.getLastName(), expected_last_name);
    }

    @Test
    public void check_phone_number_values_match(){
        assertEquals(contact.getPhoneNumber(), expected_phone_number);
    }

    @Test
    public void check_address_values_match(){
        assertEquals(contact.getAddress(), expected_address);
    }

    @Test
    public void test_setters_for_all_fields(){

        contact.setFirstName("Bob");
        contact.setLastName("Saget");
        contact.setPhoneNumber("1234567890");
        contact.setAddress("987 New Street");

        assertEquals(contact.getFirstName(), "Bob");
        assertEquals(contact.getLastName(), "Saget");
        assertEquals(contact.getPhoneNumber(), "1234567890");
        assertEquals(contact.getAddress(), "987 New Street");
    }

}