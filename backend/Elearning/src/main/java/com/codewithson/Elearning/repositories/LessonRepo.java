package com.codewithson.Elearning.repositories;

import com.codewithson.Elearning.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
}
