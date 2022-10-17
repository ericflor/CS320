package com.cs320.ProjectOne.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {

    public static final Date expected_date = new Date(2022, Calendar.DECEMBER, 25);

    public static final String expected_description = "A testing description";

    private Appointment appointment;

    @BeforeEach
    public void setUp(){
        appointment = new Appointment(2, new Date(2022, Calendar.DECEMBER, 25), "A testing description");
    }

    @Test
    public void check_date_values_match(){
        assertEquals(appointment.getDate(), expected_date);
    }

    @Test
    public void check_description_values_match(){
        assertEquals(appointment.getDescription(), expected_description);
    }

    @Test
    public void test_setters_for_all_fields(){

        final Date new_expected_date = new Date(2022, Calendar.DECEMBER, 31);
        final String new_expected_description = "New description";

        appointment.setDate(new_expected_date);
        appointment.setDescription(new_expected_description);

        assertEquals(appointment.getDate(), new_expected_date);
        assertEquals(appointment.getDescription(), new_expected_description);
    }
}
