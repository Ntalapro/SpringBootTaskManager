package com.scaler.taskmanagerspring_java.dto;

import com.scaler.taskmanagerspring_java.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateNoteResponseDTO {
    private Integer noteId;
    private NoteEntity note;
}
