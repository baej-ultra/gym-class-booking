package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    void removeUserById(Integer id);

    User findByUsername(String username);
}
