package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.dto.NewUserDto;
import org.bromanowski.classbooking.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    User addUser(NewUserDto newUser);

    Member editUserDetails(int id, Member details);

    User changeUserPassword(int id, String password);

    void deleteUserById(int id);
}
