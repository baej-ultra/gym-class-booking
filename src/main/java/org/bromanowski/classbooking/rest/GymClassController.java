package org.bromanowski.classbooking.rest;

import jakarta.validation.Valid;
import org.bromanowski.classbooking.model.GymClass;
import org.bromanowski.classbooking.service.gymclass.GymClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/classes")
public class GymClassController {

    private final GymClassService gymClassService;

    @Autowired
    public GymClassController(GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    @GetMapping
    ResponseEntity<List<GymClass>> getAllClasses() {
        return ResponseEntity.ok(gymClassService.findAll());
    }

    @GetMapping("id/{id}")
    ResponseEntity<GymClass> getClassById(@PathVariable int id) {
        GymClass gymClass = gymClassService.findById(id);
        return ResponseEntity.ok(gymClass);
    }

    @GetMapping("name/{name}")
    ResponseEntity<GymClass> getClassByName(@PathVariable String name) {
        GymClass gymClass = gymClassService.findByClassName(name);
        return ResponseEntity.ok(gymClass);
    }

    @PostMapping
    ResponseEntity<GymClass> addClass(@RequestBody @Valid GymClass gymClass) {
        GymClass newGymClass = gymClassService.addGymClass(gymClass);
        return ResponseEntity.ok(newGymClass);
    }

    @PutMapping("id/{id}")
    ResponseEntity<GymClass> editClass(@PathVariable int id, @RequestBody @Valid GymClass gymClass) {
        GymClass editedGymClass = gymClassService.editGymClass(id, gymClass);
        return ResponseEntity.ok(editedGymClass);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMember(@PathVariable int id) {
        gymClassService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
