package com.cs320.ProjectOne.controllers;

import com.cs320.ProjectOne.models.Task;
import com.cs320.ProjectOne.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable("id") int id){
        taskService.deleteTaskById(id);
    }

    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable("id") int id, @RequestBody Task task){
        return taskService.updateTaskById(id, task);
    }

    @GetMapping
    public List<Task> getTasks(){
        return taskService.getAllTasks();
    }

}
