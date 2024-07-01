package com.codewithson.Elearning.repositories;

import com.codewithson.Elearning.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
