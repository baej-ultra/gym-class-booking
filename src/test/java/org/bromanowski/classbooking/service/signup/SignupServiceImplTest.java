package org.bromanowski.classbooking.service.signup;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.exception.AlreadySignedUpException;
import org.bromanowski.classbooking.exception.EventIsFullException;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.User;
import org.bromanowski.classbooking.model.UserEvent;
import org.bromanowski.classbooking.repository.ScheduleEntryRepository;
import org.bromanowski.classbooking.repository.UserEventRepository;
import org.bromanowski.classbooking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignupServiceImplTest {

    @Mock
    private UserEventRepository userEventRepository;

    @Mock
    private ScheduleEntryRepository scheduleEntryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SignupServiceImpl signupService;

    private int id;
    private String username;

    @BeforeEach
    public void setup() {
        id = 1;
        username = "test.user";
    }

    @Test
    public void signUpForEvent_shouldSuccessfullySignUp() {
        // given

        ScheduleEntry se = new ScheduleEntry();
        se.setId(id);
        se.setCapacity(30);

        User user = new User();
        user.setUsername(username);

        when(scheduleEntryRepository.findById(id)).thenReturn(Optional.of(se));
        when(userEventRepository.countUserEventsByEvent_Id(id)).thenReturn(20);
        when(userEventRepository.existsByEvent_IdAndUser_Username(id, username)).thenReturn(false);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userEventRepository.save(any(UserEvent.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        UserEvent result = signupService.signUpForEvent(id, username);

        // then
        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(se, result.getEvent());
        verify(userEventRepository).save(any(UserEvent.class));
    }

    @Test
    public void signUpForEvent_shouldThrowEntityNotFoundException_whenEventNotFound() {
        // given
        when(scheduleEntryRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(EntityNotFoundException.class,
                () -> signupService.signUpForEvent(id, username));
        verify(userEventRepository, never()).save(any(UserEvent.class));
    }

    @Test
    public void signUpForEvent_shouldThrowEventIsFullException_whenEventFull() {
        // given
        ScheduleEntry se = new ScheduleEntry();
        se.setId(id);
        se.setCapacity(30);

        when(scheduleEntryRepository.findById(id)).thenReturn(Optional.of(se));
        when(userEventRepository.countUserEventsByEvent_Id(id)).thenReturn(30);

        // when & then
        assertThrows(EventIsFullException.class,
                () -> signupService.signUpForEvent(id, username));
        verify(userEventRepository, never()).save(any(UserEvent.class));
    }

    @Test
    public void signUpForEvent_shouldThrowAlreadySignedUpException_whenAlreadySignedUp() {
        // given
        ScheduleEntry se = new ScheduleEntry();
        se.setId(id);
        se.setCapacity(30);

        when(scheduleEntryRepository.findById(id)).thenReturn(Optional.of(se));
        when(userEventRepository.countUserEventsByEvent_Id(id)).thenReturn(20);
        when(userEventRepository.existsByEvent_IdAndUser_Username(id, username)).thenReturn(true);

        // when & then
        assertThrows(AlreadySignedUpException.class,
                () -> signupService.signUpForEvent(id, username));
        verify(userEventRepository, never()).save(any(UserEvent.class));
    }

    @Test
    public void signUpForEvent_shouldThrowEntityNotFound_whenUserNotFound() {
        // given
        ScheduleEntry se = new ScheduleEntry();
        se.setId(id);
        se.setCapacity(30);

        when(scheduleEntryRepository.findById(id)).thenReturn(Optional.of(se));
        when(userEventRepository.countUserEventsByEvent_Id(id)).thenReturn(20);
        when(userEventRepository.existsByEvent_IdAndUser_Username(id, username)).thenReturn(false);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // when & then
        assertThrows(EntityNotFoundException.class,
                () -> signupService.signUpForEvent(id, username));
        verify(userEventRepository, never()).save(any(UserEvent.class));
    }

    @Test
    public void optOut_shouldSuccessfullyOptOut() {
        // given
        UserEvent ue = new UserEvent();

        when(userEventRepository.findByEvent_IdAndUser_Username(id, username)).thenReturn(Optional.of(ue));

        // when
        signupService.optOut(id, username);

        verify(userEventRepository).delete(any(UserEvent.class));
    }

    @Test
    public void optOut_shouldThrowEntityNotFoundException_whenEventNotFound() {
        // given
        UserEvent ue = new UserEvent();

        when(userEventRepository.findByEvent_IdAndUser_Username(id, username)).thenReturn(Optional.empty());

        // when % then
        assertThrows(EntityNotFoundException.class,
                () -> signupService.optOut(id, username));

        verify(userEventRepository, never()).delete(any(UserEvent.class));
    }
}