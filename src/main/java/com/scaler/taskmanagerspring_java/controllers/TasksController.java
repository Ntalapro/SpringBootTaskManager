package com.scaler.taskmanagerspring_java.controllers;

import com.scaler.taskmanagerspring_java.dto.CreateTaskDTO;
import com.scaler.taskmanagerspring_java.dto.ErrorResponseDTO;
import com.scaler.taskmanagerspring_java.dto.TaskResponseDTO;
import com.scaler.taskmanagerspring_java.dto.UpdateTaskDTO;
import com.scaler.taskmanagerspring_java.entities.TaskEntity;
import com.scaler.taskmanagerspring_java.services.NoteService;
import com.scaler.taskmanagerspring_java.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TaskService taskService;
    private final NoteService noteService;
    private ModelMapper modelMapper = new ModelMapper();

    public TasksController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService=noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks (){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer id){
        var task = taskService.getTaskByID(id);
        var notes = noteService.getNotesForTask(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }

        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        taskService.addtask(body.getTitle(),body.getDescription(), body.getDeadline());


        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Integer id,@RequestBody UpdateTaskDTO body) throws ParseException {
        taskService.updatetask(id,body.getDescription(), body.getDeadline(), body.getCompleted());
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if( e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("invalid date format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }
}
