package com.codewithson.Elearning.services.course;

import com.codewithson.Elearning.dtos.CourseDTO;
import com.codewithson.Elearning.models.Course;
import com.codewithson.Elearning.response.course.CourseListResponse;
import com.codewithson.Elearning.response.course.CourseResponse;
import org.springframework.data.domain.PageRequest;

public interface ICourseService {
    CourseResponse createCourse(CourseDTO courseDTO) throws Exception;

    void deleteCourse(Long id);

    Course getCourseById(Long id) throws Exception;;

    CourseResponse updateCourse(CourseDTO courseDTO, Long id) throws Exception;

    CourseListResponse getCourses(Long categoryId, PageRequest pageRequest);
}
