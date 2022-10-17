package com.cs320.ProjectOne.services;

import com.cs320.ProjectOne.models.Appointment;
import com.cs320.ProjectOne.repositories.AppointmentRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {

    @Autowired
    private final AppointmentRepo appointmentRepo;

    public Appointment createOne(Appointment appointment){
        return appointmentRepo.save(appointment);
    }

    public void deleteAppointment(int apptId){
        appointmentRepo.deleteById(apptId);
    }

    public List<Appointment> getAllAppts(){
        return appointmentRepo.findAll();
    }
}
