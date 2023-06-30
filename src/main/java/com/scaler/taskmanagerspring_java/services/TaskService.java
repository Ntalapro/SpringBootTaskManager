package com.scaler.taskmanagerspring_java.services;


import com.scaler.taskmanagerspring_java.entities.NoteEntity;
import com.scaler.taskmanagerspring_java.entities.TaskEntity;
import com.scaler.taskmanagerspring_java.entities.TaskEntity;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class TaskService {

    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineFormatter =  new SimpleDateFormat("yyyy-MM-dd");
    public TaskEntity addtask(String title, String description, String deadline ) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineFormatter.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;

        return task;
    }

    public ArrayList<TaskEntity> getTasks(){
        return tasks;

    }

    public TaskEntity getTaskByID(int id){
        for(TaskEntity task: tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;

    }

    public TaskEntity updatetask(int id, String description, String deadline, Boolean completed) throws  ParseException{
        TaskEntity task  = getTaskByID(id);
        if(task == null){
            return null;
        }
        if(description != null){
            task.setDescription(description);
        }
        if(deadline != null){
            task.setDeadline(deadlineFormatter.parse(deadline));
        }
        if(completed != null){
            task.setCompleted(completed);
        }

        return task;
    }

}

