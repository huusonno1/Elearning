package com.codewithson.Elearning.controllers;

import com.codewithson.Elearning.components.SecurityUtils;
import com.codewithson.Elearning.dtos.CourseDTO;
import com.codewithson.Elearning.dtos.UserLoginDTO;
import com.codewithson.Elearning.models.Course;
import com.codewithson.Elearning.models.User;
import com.codewithson.Elearning.response.ResponseObject;
import com.codewithson.Elearning.response.course.CourseListResponse;
import com.codewithson.Elearning.response.course.CourseResponse;
import com.codewithson.Elearning.services.course.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/course")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService courseService;
    private final SecurityUtils securityUtils;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> createCourse (
            @Valid @RequestBody CourseDTO courseDTO,
            BindingResult result
    ) throws Exception {
        ResponseEntity<ResponseObject> validationResponse = handleValidationErrors(result);

        User loginUser = securityUtils.getLoggedInUser();

        if(courseDTO.getAdminId() == null){
            courseDTO.setAdminId(loginUser.getId());
        }

        CourseResponse courseResponse = courseService.createCourse(courseDTO);

        return ResponseEntity.ok(ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .data(courseResponse)
                        .message("created successful")
                        .build());
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> deleteCourse(
            @PathVariable Long id
    )throws Exception {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message(String.format("Course with id = %d deleted successfully", id))
                        .data(null)
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseDTO courseDTO,
            BindingResult result
    ) throws Exception{
        ResponseEntity<ResponseObject> validationResponse = handleValidationErrors(result);

        User loginUser = securityUtils.getLoggedInUser();

        if (courseDTO.getAdminId() == null) {
            courseDTO.setAdminId(loginUser.getId());
        }

        CourseResponse courseResponse = courseService.updateCourse(courseDTO, id);
        return ResponseEntity.ok(ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .data(courseResponse)
                        .message("update is successfully")
                        .build());
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> getCourseById(
            @PathVariable Long id
    ) throws Exception {
        CourseResponse courseResponse = courseService.getCourseById(id);

        return ResponseEntity.ok(ResponseObject.builder()
                .status(HttpStatus.OK)
                .data(courseResponse)
                .message("created successful")
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getCourses(
            @RequestParam(defaultValue = "0", name = "category_id") Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ){
        PageRequest pageRequest = PageRequest.of(page, limit);

        CourseListResponse courseListResponse = courseService.getCourses(categoryId, pageRequest);
        return ResponseEntity.ok(ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .message("Get courses successfully")
                        .data(courseListResponse)
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
