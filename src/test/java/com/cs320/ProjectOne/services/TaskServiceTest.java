package com.cs320.ProjectOne.services;

import com.cs320.ProjectOne.models.Task;
import com.cs320.ProjectOne.repositories.TaskRepo;
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
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService(taskRepo);
        task = new Task(3, "TestTask", "This is a testing task");
    }

    @Test
    void should_create_a_new_task(){
        given(taskRepo.save(this.task)).willReturn(task);
        Task task1 = taskService.createTask(this.task);
        assertThat(task1).isNotNull();
        assertEquals(task1, this.task);
    }

    @Test
    public void givenTaskId_whenDeleteTask_thenNothing() {
        willDoNothing().given(taskRepo).deleteById(task.getId());
        taskService.deleteTaskById(task.getId());
        verify(taskRepo, times(1)).deleteById(task.getId());
    }

    @Test
    @Sql("/Users/m_2171923/Desktop/SNHU/CS-320/ProjectOne/src/main/resources/data.sql")
    void givenTaskObject_whenUpdateTask_thenReturnUpdatedTask() {
        task = taskService.getTaskById(1);
        given(taskRepo.save(task)).willReturn(task);
        task.setName("New Task");
        task.setDescription("New Testing Task Description");
        Task updatedTask = taskService.updateTaskById(1, task);
        assertThat(updatedTask.getName()).isEqualTo("New Task");
        assertThat(updatedTask.getDescription()).isEqualTo("New Testing Task Description");
    }

    @Test
    void name_input_meets_character_constraint() {

        Task newTask = new Task(2, "New Task Test New Task Test", "New Description");

        if(newTask.getName().length() > 20) {
            System.out.println("Name is longer than 20 characters, please enter shorter name");
            when(taskRepo.save(newTask)).thenThrow(ConstraintViolationException.class);
            assertThrows(ConstraintViolationException.class, () -> taskService.createTask(newTask));
        }

        else {
            when(taskRepo.save(any())).thenReturn(newTask);
            Task newTask1 = taskService.createTask(newTask);
            assertEquals(newTask1, newTask);
        }

    }

    @Test
    void description_input_meets_character_constraint() {

        Task newTask = new Task(2, "New Task Test", "New Description New Description New Description New Description New Description New Description New Description New Description New Description");

        if(newTask.getDescription().length() > 50){
            System.out.println("Description is longer than 50 characters, please enter shorter description");
            when(taskRepo.save(newTask)).thenThrow(ConstraintViolationException.class);
            assertThrows(ConstraintViolationException.class, () -> taskService.createTask(newTask));
        }

        else {
            when(taskRepo.save(any())).thenReturn(newTask);
            Task newTask1 = taskService.createTask(newTask);
            assertEquals(newTask1, newTask);
        }

    }
}