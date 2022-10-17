package com.cs320.ProjectOne.controllers;

import com.cs320.ProjectOne.models.Appointment;
import com.cs320.ProjectOne.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    @Autowired
    private final AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment){
        return appointmentService.createOne(appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable("id") int id){
        appointmentService.deleteAppointment(id);
    }

    @GetMapping
    public List<Appointment> getAllAppts(){
        return appointmentService.getAllAppts();
    }
}
