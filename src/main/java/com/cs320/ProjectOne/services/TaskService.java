package com.cs320.ProjectOne.services;

import com.cs320.ProjectOne.models.Task;
import com.cs320.ProjectOne.repositories.TaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    @Autowired
    private final TaskRepo taskRepo;

    public Task createTask(Task task){
        return taskRepo.save(task);
    }

    public void deleteTaskById(int taskId){
        taskRepo.deleteById(taskId);
    }

    public Task updateTaskById(int taskId, Task task){

        Task dbTask = getTaskById(taskId);
        dbTask.setName(task.getName());
        dbTask.setDescription(task.getDescription());

        return taskRepo.save(dbTask);
    }

    public List<Task> getAllTasks(){
        return taskRepo.findAll();
    }
    public Task getTaskById(int taskId){

        return taskRepo.getReferenceById(taskId);
    }
}
