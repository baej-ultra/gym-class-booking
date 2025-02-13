package org.bromanowski.classbooking.rest;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.exception.AlreadySignedUpException;
import org.bromanowski.classbooking.exception.EventIsFullException;
import org.bromanowski.classbooking.exception.handler.RestExceptionHandler;
import org.bromanowski.classbooking.exception.response.StandardErrorResponse;
import org.bromanowski.classbooking.service.signup.SignupService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignupControllerTest {

    @Mock
    private SignupService signupService;

    @InjectMocks
    private SignupController signupController;

    static private Jwt jwt;
    static private int eventId;
    static private String username;

    static private RestExceptionHandler exceptionHandler;

    @BeforeAll
    static void setUp() {
        eventId = 1;
        username = "test.user";
        jwt = new Jwt("dummy-token", Instant.MIN, Instant.MAX,
                Map.of("header","val"),
                Map.of("sub", username));
        exceptionHandler = new RestExceptionHandler();
    }

    @Test
    public void signUpForEvent_shouldSuccessfullySignUpForEvent() {
        // when
        ResponseEntity<String> response = signupController.signUpForEvent(jwt, eventId);

        // then
        verify(signupService).signUpForEvent(eventId, username);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void signUpForEvent_shouldReturnNotFound_whenNoEventFound() {
        // given
        doThrow(new EntityNotFoundException("signUpForEvent_shouldReturnNotFound_whenNoEventFound"))
                .when(signupService).signUpForEvent(eventId, username);

        // when
        EntityNotFoundException thrownException = assertThrows(
                EntityNotFoundException.class,
                () -> signupController.signUpForEvent(jwt, eventId)
        );

        // then
        ResponseEntity<StandardErrorResponse> response = exceptionHandler.handleException(thrownException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(signupService).signUpForEvent(eventId, username);
    }

    @Test
    public void signUpForEvent_shouldReturnConflict_whenAlreadySignedUp() {
        // given
        doThrow(new AlreadySignedUpException("signUpForEvent_shouldReturnConflict_whenAlreadySignedUp"))
                .when(signupService).signUpForEvent(eventId, username);

        // when
        AlreadySignedUpException thrownException = assertThrows(
                AlreadySignedUpException.class,
                () -> signupController.signUpForEvent(jwt, eventId)
        );

        // then
        ResponseEntity<StandardErrorResponse> response = exceptionHandler.handleException(thrownException);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(signupService).signUpForEvent(eventId, username);
    }

    @Test
    public void signUpForEvent_shouldReturnBadReq_whenEventIsFull() {
        // given
        doThrow(new EventIsFullException("signUpForEvent_shouldReturnBadReq_whenEventIsFull"))
                .when(signupService).signUpForEvent(eventId, username);

        // when
        EventIsFullException thrownException = assertThrows(
                EventIsFullException.class,
                () -> signupController.signUpForEvent(jwt, eventId)
        );

        // then
        ResponseEntity<StandardErrorResponse> response = exceptionHandler.handleException(thrownException);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(signupService).signUpForEvent(eventId, username);
    }

    @Test
    public void optOutFromEvent_shouldOptOut() {
        // when
        ResponseEntity<Void> response = signupController.optOutFromEvent(jwt, eventId);

        // then
        verify(signupService).optOut(eventId, username);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void optOutFromEvent_shouldReturnNotFound_whenUserNotSignedUp() {
        // given
        doThrow(new EntityNotFoundException("optOutFromEvent_shouldReturnNotFound_whenUserNotSignedUp"))
                .when(signupService).optOut(eventId, username);

        // when
        EntityNotFoundException thrownException = assertThrows(
                EntityNotFoundException.class,
                () -> signupController.optOutFromEvent(jwt, eventId)
        );

        // then
        ResponseEntity<StandardErrorResponse> response = exceptionHandler.handleException(thrownException);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(signupService).optOut(eventId, username);
    }


}
