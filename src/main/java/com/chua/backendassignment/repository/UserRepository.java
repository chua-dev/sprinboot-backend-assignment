package com.chua.backendassignment.repository;

import com.chua.backendassignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByGroupId(Long postId);
}
