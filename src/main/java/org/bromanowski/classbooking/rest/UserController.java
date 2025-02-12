package org.bromanowski.classbooking.rest;

import jakarta.validation.Valid;
import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.User;
import org.bromanowski.classbooking.model.dto.NewUserDto;
import org.bromanowski.classbooking.service.user.UserService;
import org.hibernate.validator.constraints.Length;
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

    @PutMapping("/users/id/{id}/details")
    ResponseEntity<Member> editUsersDetails(@RequestBody @Valid Member member, @PathVariable Integer id) {
        Member editedMember = userService.editUserDetails(id, member);
        return ResponseEntity.ok(editedMember);
    }

    @PutMapping("/users/id/{id}/changepassword")
    ResponseEntity<String> changePassword(@RequestBody @Length(min = 2) String newPassword,
                                          @PathVariable int id) {
        userService.changeUserPassword(id, newPassword);
        return ResponseEntity.ok("Password changed");
    }

    @DeleteMapping("/users/id/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
