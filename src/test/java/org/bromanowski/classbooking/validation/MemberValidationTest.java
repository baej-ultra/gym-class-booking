package org.bromanowski.classbooking.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.bromanowski.classbooking.model.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberValidationTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void firstNameShouldFailWhenTooShort() {
        Member member = new Member(null, "a", "Doe", "test@example.com"); // "a" is too short
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        ConstraintViolation<Member> violation = violations.iterator().next();
        assertEquals("First name has to be at least 2 characters", violation.getMessage());
        assertEquals("firstName", violation.getPropertyPath().toString());
    }

    @Test
    void firstNameShouldPassWhenValid() {
        Member member = new Member(null, "John", "Doe", "test@example.com");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertTrue(violations.isEmpty());
    }

    @Test
    void lastNameShouldFailWhenTooShort() {
        Member member = new Member(null, "John", "b", "test@example.com"); // "b" is too short
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        ConstraintViolation<Member> violation = violations.iterator().next();
        assertEquals("Last name has to be at least 2 characters", violation.getMessage());
        assertEquals("lastName", violation.getPropertyPath().toString());
    }

    @Test
    void lastNameShouldPassWhenValid() {
        Member member = new Member(null, "John", "Doe", "test@example.com");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertTrue(violations.isEmpty());
    }


    @Test
    void emailShouldFailWhenInvalidFormat() {
        Member member = new Member(null, "John", "Doe", "invalid-email");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        ConstraintViolation<Member> violation = violations.iterator().next();
        assertEquals("Invalid email format. Please provide a valid email address.", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    void emailShouldFailWhenEmpty() {
        Member member = new Member(null, "John", "Doe", ""); // Empty email
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        assertEquals(1, violations.size());
        ConstraintViolation<Member> violation = violations.iterator().next();
        assertEquals("E-mail cannot be empty", violation.getMessage());
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    void emailShouldPassWhenValid() {
        Member member = new Member(null, "John", "Doe", "test@example.com");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertTrue(violations.isEmpty());
    }

    @Test
    void memberShouldPassWhenAllFieldsValid() {
        Member member = new Member(null, "John", "Doe", "test@example.com");
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        assertTrue(violations.isEmpty());
    }

}