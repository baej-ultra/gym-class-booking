package org.bromanowski.classbooking.rest;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.exception.AlreadySignedUpException;
import org.bromanowski.classbooking.exception.EventIsFullException;
import org.bromanowski.classbooking.service.signup.SignupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignupController.class)
public class SignupControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private SignupService signupService;

    private Jwt jwtToken;

    @BeforeEach
    public void setUp() {
        jwtToken = Jwt.withTokenValue("dummy-token")
                .header("alg", "HS256")
                .subject("test.user")
                .claim("scope", "ROLE_MEMBER")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60))
                .build();
    }

    @Test
    public void signUpForEvent_shouldSignUpSuccessfully() throws Exception {
        // when & then
        mvc.perform(post("/api/signup")
                                .param("event", "1")
                                .with(jwt().jwt(jwtToken)))
                .andExpect(status().isOk());

        verify(signupService).signUpForEvent(1, "test.user");
    }

    @Test
    public void signUpForEvent_shouldReturnConflict_whenAlreadySignedUp() throws Exception {
        // given
        doThrow(new AlreadySignedUpException("test"))
                .when(signupService)
                .signUpForEvent(1, "test.user");

        // when & then
        mvc.perform(post("/api/signup")
                        .param("event", "1")
                        .with(jwt().jwt(jwtToken)))
                .andExpect(status().isConflict());
        verify(signupService).signUpForEvent(1, "test.user");
    }

    @Test
    public void signUpForEvent_shouldReturnNotFound_whenNoSuchEvent() throws Exception {
        // given
        doThrow(new EntityNotFoundException("test"))
                .when(signupService)
                .signUpForEvent(1, "test.user");

        // when & then
        mvc.perform(post("/api/signup")
                        .param("event", "1")
                        .with(jwt().jwt(jwtToken)))
                .andExpect(status().isNotFound());
        verify(signupService).signUpForEvent(1, "test.user");
    }

    @Test
    public void signUpForEvent_shouldReturnBadReq_whenEventIsFull() throws Exception {
        // given
        doThrow(new EventIsFullException("test"))
                .when(signupService)
                .signUpForEvent(1, "test.user");

        // when & then
        mvc.perform(post("/api/signup")
                        .param("event", "1")
                        .with(jwt().jwt(jwtToken)))
                .andExpect(status().isBadRequest());
        verify(signupService).signUpForEvent(1, "test.user");
    }

    @Test
    public void optOutFromEvent_shouldOptOut() throws Exception {
        mvc.perform(delete("/api/signup")
                        .param("event", "1")
                        .with(jwt().jwt(jwtToken)))
                .andExpect(status().isNoContent());

        verify(signupService).optOut(1, "test.user");
    }

    @Test
    public void optOutFromEvent_shouldReturnNotFound_whenUserNotSignedUp() throws Exception {
        doThrow(new EntityNotFoundException("test"))
                .when(signupService)
                .optOut(1, "test.user");

        mvc.perform(delete("/api/signup")
                        .param("event", "1")
                        .with(jwt().jwt(jwtToken)))
                .andExpect(status().isNotFound());

        verify(signupService).optOut(1, "test.user");
    }
}
