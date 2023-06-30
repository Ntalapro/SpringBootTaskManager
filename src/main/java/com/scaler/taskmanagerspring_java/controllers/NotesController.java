package com.scaler.taskmanagerspring_java.controllers;

import com.scaler.taskmanagerspring_java.dto.CreateNoteDTO;
import com.scaler.taskmanagerspring_java.dto.CreateNoteResponseDTO;
import com.scaler.taskmanagerspring_java.entities.NoteEntity;
import com.scaler.taskmanagerspring_java.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private final NoteService noteService;
    public NotesController(NoteService noteService){
        this.noteService=noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer id){
        var notes = noteService.getNotesForTask(id);

        return ResponseEntity.ok(notes);
    }
    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body)  {
        var note = noteService.addNoteforTask(taskId,body.getTitle(),body.getBody());

        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId,note));
    }
}
