package org.bromanowski.classbooking.rest;

import jakarta.validation.Valid;
import org.bromanowski.classbooking.model.Instructor;
import org.bromanowski.classbooking.service.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    ResponseEntity<List<Instructor>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.findAll());
    }

    @GetMapping("/id/{id}")
    ResponseEntity<Instructor> getInstructorById(@PathVariable int id) {
        Instructor instructor = instructorService.findById(id);
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    ResponseEntity<Instructor> addInstructor(@RequestBody @Valid Instructor instructor) {
        Instructor newInstructor = instructorService.addInstructor(instructor);
        return ResponseEntity.ok(newInstructor);
    }

    @PutMapping("/id/{id}")
    ResponseEntity<Instructor> editInstructorById(@PathVariable int id, @RequestBody @Valid Instructor instructor) {
        Instructor editedInstructor = instructorService.editInstructor(id, instructor);
        return ResponseEntity.ok(editedInstructor);
    }

    @DeleteMapping("/id/{id}")
    ResponseEntity<Void> deleteInstructor(@PathVariable int id) {
        instructorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
