package com.codewithson.Elearning.controllers;

import com.codewithson.Elearning.dtos.LessonDTO;
import com.codewithson.Elearning.response.ResponseObject;
import com.codewithson.Elearning.response.lesson.LessonResponse;
import com.codewithson.Elearning.services.lesson.ILessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/modules/{moduleId}/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final ILessonService lessonService;

    //    CREATE
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> createLesson (
            @PathVariable Long moduleId,
            @Valid @RequestBody LessonDTO lessonDTO,
            BindingResult result
    ) throws Exception {
        ResponseEntity<ResponseObject> validationResponse = handleValidationErrors(result);

        LessonResponse lessonResponse = lessonService.createLesson(moduleId, lessonDTO);

        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(lessonResponse)
                .message("update is successfully")
                .build());
    }

    @PutMapping("/{lessonId}/upload-video")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> uploadVideo (
            @PathVariable Long lessonId,
            MultipartFile file
    ) throws Exception {

        LessonResponse lessonResponse = lessonService.createVideo(lessonId, file);

        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(lessonResponse)
                .message("update is successfully")
                .build());
    }

    public ResponseEntity<ResponseObject> handleValidationErrors(BindingResult result) {
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .message(errorMessages.toString())
                    .build());
        }
        return null; // No validation errors
    }
}
