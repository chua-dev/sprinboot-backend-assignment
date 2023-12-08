package com.chua.backendassignment.repository;

import com.chua.backendassignment.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByName(String name);
    boolean existsByName(String name);
    boolean existsByCode(String code);
    List<Group> findByActiveTrue();
    List<Group> findByDescriptionContaining(String description);
}
