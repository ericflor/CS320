package com.cs320.ProjectOne.services;

import com.cs320.ProjectOne.models.Appointment;
import com.cs320.ProjectOne.repositories.AppointmentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepo appointmentRepo;

    @InjectMocks
    private AppointmentService appointmentService;

    private Appointment appointment;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        appointmentService = new AppointmentService(appointmentRepo);
        appointment = new Appointment(3, new Date(2023, Calendar.APRIL, 23), "This is a testing appointment");
    }

    @Test
    void should_create_new_appointment(){
        given(appointmentRepo.save(this.appointment)).willReturn(appointment);
        Appointment appointment1 = appointmentService.createOne(this.appointment);
        assertThat(appointment1).isNotNull();
        assertEquals(appointment1, this.appointment);
    }

    @Test
    public void givenApptId_whenDeleteAppt_thenNothing() {
        willDoNothing().given(appointmentRepo).deleteById(appointment.getId());
        appointmentService.deleteAppointment(appointment.getId());
        verify(appointmentRepo, times(1)).deleteById(appointment.getId());
    }

    @Test
    void description_input_meets_character_constraint() {

        Appointment newAppt = new Appointment(5, new Date(), "New Description New Description New Description New Description New Description New Description New Description New Description");

        if(newAppt.getDescription().length() > 50) {
            System.out.println("Description is longer than 50 characters, please enter shorter description");
            when(appointmentRepo.save(newAppt)).thenThrow(ConstraintViolationException.class);
            assertThrows(ConstraintViolationException.class, () -> appointmentService.createOne(newAppt));
        }

        else {
            when(appointmentRepo.save(any())).thenReturn(newAppt);
            Appointment newTask1 = appointmentService.createOne(newAppt);
            assertEquals(newTask1, newAppt);
        }

    }

    @Test
    void date_input_meets_constraint_of_not_in_past() {

        Appointment newAppt = new Appointment(5, new Date(2021, Calendar.JUNE, 20), "New Description");
        Date newDate = new Date(2023, Calendar.APRIL, 25);

        if(newAppt.getDate().getYear() < newDate.getYear()) {
            System.out.println("Chosen date is in the past, please enter a date in the future for your appointment");
            when(appointmentRepo.save(newAppt)).thenThrow(ConstraintViolationException.class);
            assertThrows(ConstraintViolationException.class, () -> appointmentService.createOne(newAppt));
        }

        else {
            when(appointmentRepo.save(any())).thenReturn(newAppt);
            Appointment newTask1 = appointmentService.createOne(newAppt);
            assertEquals(newTask1, newAppt);
        }

    }
}
