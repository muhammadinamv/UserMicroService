package com.project.usermicroservice.repositories;

import com.project.usermicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    User save(User entity);
    Optional<User> findByEmail(String email);

}
