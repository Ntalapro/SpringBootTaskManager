package com.scaler.taskmanagerspring_java.services;


import com.scaler.taskmanagerspring_java.entities.NoteEntity;
import com.scaler.taskmanagerspring_java.entities.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class NoteService {
    private TaskService taskService = new TaskService();
    private HashMap<Integer,TaskNotesHolder> taskNotesHolders = new HashMap<>();
    public NoteService(TaskService taskService){
        this.taskService=taskService;
    }
    class TaskNotesHolder{
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();

    }

    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task = taskService.getTaskByID(taskId);
        if(task == null){
            return null;
        }
        if(taskNotesHolders.get(taskId) == null){
            taskNotesHolders.put(taskId,new TaskNotesHolder());
        }
        return taskNotesHolders.get(taskId).notes;
    }

    public NoteEntity addNoteforTask(int taskId,String title,String body){
        TaskEntity task = taskService.getTaskByID(taskId);
        if(task == null){
            return null;
        }
        if(taskNotesHolders.get(taskId) == null){
            taskNotesHolders.put(taskId,new TaskNotesHolder());
        }
        TaskNotesHolder taskNotesHolder = taskNotesHolders.get(taskId);
        NoteEntity note = new NoteEntity();
        note.setId(taskNotesHolder.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;

        return note;
    }
}
