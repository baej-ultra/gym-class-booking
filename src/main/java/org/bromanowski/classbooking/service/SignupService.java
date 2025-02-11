package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.UserEvent;

public interface SignupService {

    UserEvent signUpForEvent(int eventId, String username);

    void optOut(int eventId, String username);
}
