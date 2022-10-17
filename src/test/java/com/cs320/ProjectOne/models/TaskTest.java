package com.cs320.ProjectOne.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    public static final String expected_name = "Test Task";

    public static final String expected_description = "This is the test description";

    private Task task;

    @BeforeEach
    public void setUp(){
        task = new Task(5, "Test Task", "This is the test description");
    }

    @Test
    public void check_name_values_match(){
        assertEquals(task.getName(), expected_name);
    }

    @Test
    public void check_description_values_match(){
        assertEquals(task.getDescription(), expected_description);
    }

    @Test
    public void test_setters_for_all_fields(){

        task.setName("New Test Task");
        task.setDescription("New test description");

        assertEquals(task.getName(), "New Test Task");
        assertEquals(task.getDescription(), "New test description");
    }}