package com.codewithson.Elearning.repositories;

import com.codewithson.Elearning.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Long> {
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.modules WHERE c.id = ?1")
    Optional<Course> getDetailCourse(Long courseId);

    @Query("SELECT c FROM Course c WHERE "+
            " (?1 IS NULL OR ?1 = 0 OR c.category.id = ?1)")
    Page<Course> getAllCourses(Long categoryId, Pageable pageable);
}
