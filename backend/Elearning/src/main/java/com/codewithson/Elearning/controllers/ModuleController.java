package com.codewithson.Elearning.controllers;

import com.codewithson.Elearning.dtos.ModuleDTO;
import com.codewithson.Elearning.response.ResponseObject;
import com.codewithson.Elearning.response.module.ModuleListResponse;
import com.codewithson.Elearning.response.module.ModuleResponse;
import com.codewithson.Elearning.services.module.IModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/course/{courseId}/modules")
@RequiredArgsConstructor
public class ModuleController {
    private final IModuleService moduleService;

    //    CREATE
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> createModule (
            @PathVariable Long courseId,
            @Valid @RequestBody ModuleDTO moduleDTO,
            BindingResult result
    ) throws Exception {
        ResponseEntity<ResponseObject> validationResponse = handleValidationErrors(result);

        ModuleResponse moduleResponse = moduleService.createModule(courseId, moduleDTO);

        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(moduleResponse)
                .message("update is successfully")
                .build());
    }

//    READ

    @GetMapping("")
    public ResponseEntity<ResponseObject> getModules (
            @PathVariable Long courseId
    ) throws Exception {

        ModuleListResponse moduleListResponse = moduleService.getAllModules(courseId);

        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(moduleListResponse)
                .message("update is successfully")
                .build());
    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<ResponseObject> getModuleById (
            @PathVariable Long courseId,
            @PathVariable Long moduleId
    ) throws Exception {
        ModuleResponse moduleResponse = moduleService.getModuleById(courseId, moduleId);

        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(moduleResponse)
                .message("update is successfully")
                .build());
    }

//    UPDATE
    @PutMapping("/{moduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> updateModule (
            @PathVariable Long courseId,
            @PathVariable Long moduleId,
            @Valid @RequestBody ModuleDTO moduleDTO,
            BindingResult result
    ) throws Exception {
        ResponseEntity<ResponseObject> validationResponse = handleValidationErrors(result);

        ModuleResponse moduleResponse = moduleService.updateModule(courseId, moduleId, moduleDTO);

        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(moduleResponse)
                .message("update is successfully")
                .build());
    }

//    DELETE
    @DeleteMapping("/{moduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> deleteModule (
            @PathVariable Long courseId,
            @PathVariable Long moduleId
    ) throws Exception {
        moduleService.deleteModule(courseId, moduleId);
        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .message(String.format("Course with id = %d deleted module with id = %d successfully", courseId, moduleId))
                .data(null)
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
