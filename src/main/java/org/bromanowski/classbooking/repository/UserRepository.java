package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    void removeUserById(Integer id);

    Optional<User> findByUsername(String username);
}
