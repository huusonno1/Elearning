package com.codewithson.Elearning.repositories;

import com.codewithson.Elearning.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.modules WHERE c.id = ?1")
    Optional<Course> getDetailCourse(Long courseId);
}
