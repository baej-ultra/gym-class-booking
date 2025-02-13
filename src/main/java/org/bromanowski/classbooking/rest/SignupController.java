package org.bromanowski.classbooking.rest;

import org.bromanowski.classbooking.service.signup.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

    private final SignupService signupService;

    @Autowired
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping
    ResponseEntity<String> signUpForEvent(@AuthenticationPrincipal Jwt jwt,
                                             @RequestParam(name = "event") Integer eventId) {
        String username = jwt.getSubject();
        signupService.signUpForEvent(eventId, username);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    ResponseEntity<Void> optOutFromEvent(@AuthenticationPrincipal Jwt jwt,
                                             @RequestParam(name = "event") Integer eventId) {
        String username = jwt.getSubject();
        signupService.optOut(eventId, username);
        return ResponseEntity.noContent().build();
    }
}
