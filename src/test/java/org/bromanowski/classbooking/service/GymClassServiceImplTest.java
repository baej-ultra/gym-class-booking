package org.bromanowski.classbooking.service;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.model.GymClass;
import org.bromanowski.classbooking.repository.GymClassRepository;
import org.bromanowski.classbooking.service.gymclass.GymClassServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GymClassServiceImplTest {

    @Mock
    GymClassRepository gymClassRepository;

    @InjectMocks
    GymClassServiceImpl gymClassService;

    @Test
    public void findAll_shouldReturnAllClasses() {
        GymClass class1 = new GymClass();
        GymClass class2 = new GymClass();
        List<GymClass> classes = List.of(class1, class2);

        when(gymClassRepository.findAll()).thenReturn(classes);

        List<GymClass> result = gymClassService.findAll();

        assertEquals(2, result.size());
        assertEquals(result.get(0), classes.get(0));
        assertEquals(result.get(1), classes.get(1));
        verify(gymClassRepository).findAll();
    }

    @Test
    public void findById_shouldReturn_whenIdExists() {
        int id = 1;
        GymClass gymClass = new GymClass();
        gymClass.setId(id);

        when(gymClassRepository.findById(id)).thenReturn(Optional.of(gymClass));

        GymClass result = gymClassService.findById(id);

        assertEquals(result, gymClass);
        verify(gymClassRepository).findById(id);
    }

    @Test
    public void findById_shouldThrowException_whenIdDoesNotExist() {
        int id = 1;
        when(gymClassRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                gymClassService.findById(id));
        verify(gymClassRepository).findById(id);
    }

    @Test
    public void findByName_shouldReturn_whenNameExists() {
        String name = "Class name";
        GymClass gymClass = new GymClass();
        gymClass.setClassName(name);

        when(gymClassRepository.findByClassName(name)).thenReturn(Optional.of(gymClass));

        GymClass result = gymClassService.findByClassName(name);

        assertEquals(result, gymClass);
    }

    @Test
    public void findByName_shouldThrowException_whenNameDoesNotExist() {
        String name = "Class name";

        GymClass gymClass = new GymClass();
        gymClass.setClassName(name);

        when(gymClassRepository.findByClassName(name)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                gymClassService.findByClassName(name));
    }

    @Test
    public void editGymClass_shouldReturn_whenIdIsFound() {
        int id = 1;

        GymClass gymClass = new GymClass();
        gymClass.setId(id);

        when(gymClassRepository.existsById(id)).thenReturn(true);
        when(gymClassRepository.save(any(GymClass.class))).thenReturn(gymClass);

        gymClassService.addGymClass(gymClass);
        GymClass result = gymClassService.editGymClass(id, gymClass);

        assertEquals(result, gymClass);
    }

    @Test
    public void editGymClass_shouldThrowException_whenIdIsNotFound() {
        int id = 1;
        when(gymClassRepository.existsById(1)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () ->
                gymClassService.editGymClass(id, new GymClass()));
    }

    @Test
    public void deleteGymClass_shouldReturn_whenIdIsFound() {
        int id = 1;
        when(gymClassRepository.existsById(id)).thenReturn(true);

        gymClassService.deleteById(id);

        verify(gymClassRepository, times(1)).deleteById(id);
    }

    @Test
    public void deleteGymClass_shouldThrowException_whenIdIsNotFound() {
        int id = 1;
        when(gymClassRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> gymClassService.deleteById(id));
    }
}