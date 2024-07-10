package com.codewithson.Elearning.repositories;

import com.codewithson.Elearning.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModuleRepo extends JpaRepository<Module, Long> {
    @Query("select m from Module m where m.course.id = ?1 and m.id = ?2 ")
    Optional<Module> findByCourseIdAndId(Long courseId, Long moduleId);
    @Query("select m from Module m where m.course.id = ?1 ")
    List<Module> findByCourseId(Long courseId);
}
