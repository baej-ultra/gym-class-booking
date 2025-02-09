package org.bromanowski.classbooking.rest;

import jakarta.validation.Valid;
import org.bromanowski.classbooking.entity.Instructor;
import org.bromanowski.classbooking.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    ResponseEntity<List<Instructor>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Instructor> getInstructorById(@PathVariable int id) {
        Instructor instructor = instructorService.findById(id);
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    ResponseEntity<Instructor> addInstructor(Instructor instructor) {
        Instructor newInstructor = instructorService.addInstructor(instructor);
        return ResponseEntity.ok(newInstructor);
    }

    @PutMapping("/{id}")
    ResponseEntity<Instructor> editInstrucotrById(@PathVariable int id, @RequestBody @Valid Instructor instructor) {
        Instructor editedInstructor = instructorService.editInstructor(id, instructor);
        return ResponseEntity.ok(editedInstructor);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteInstructor(@PathVariable int id) {
        instructorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
