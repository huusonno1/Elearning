package com.codewithson.Elearning.repositories;

import com.codewithson.Elearning.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface RoleRepo extends JpaRepository<Role, Long> {
}
