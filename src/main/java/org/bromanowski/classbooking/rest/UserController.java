package org.bromanowski.classbooking.rest;

import jakarta.validation.Valid;
import org.bromanowski.classbooking.model.NewUserDto;
import org.bromanowski.classbooking.model.User;
import org.bromanowski.classbooking.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/id/{id}")
    ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    ResponseEntity<User> addNewUser(@RequestBody @Valid NewUserDto user) {
        User newUser = userService.addUser(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/users/id/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
