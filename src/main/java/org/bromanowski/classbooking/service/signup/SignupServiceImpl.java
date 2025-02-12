package org.bromanowski.classbooking.service.signup;


import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.UserEvent;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.User;
import org.bromanowski.classbooking.repository.ScheduleEntryRepository;
import org.bromanowski.classbooking.repository.UserEventRepository;
import org.bromanowski.classbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService{

    private final ScheduleEntryRepository scheduleEntryRepository;
    private final UserRepository userRepository;
    private final UserEventRepository userEventRepository;

    @Autowired
    public SignupServiceImpl(ScheduleEntryRepository scheduleEntryRepository, UserRepository userRepository, UserEventRepository userEventRepository) {
        this.scheduleEntryRepository = scheduleEntryRepository;
        this.userRepository = userRepository;
        this.userEventRepository = userEventRepository;
    }

    @Override
    public UserEvent signUpForEvent(int eventId, String username) {
        ScheduleEntry event = scheduleEntryRepository.findById(eventId).orElseThrow(() ->
                new EntityNotFoundException("Event not found for id: " + eventId));
        int maxCapacity = event.getCapacity();
        int spotsTaken = userEventRepository.countUserEventsByEvent_Id(eventId);
        String className = event.getClass().getName();

        if (maxCapacity < spotsTaken + 1) {
            throw new RuntimeException("Event %s at max capacity (%d). No spots available".formatted(className, maxCapacity));
        }

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new EntityNotFoundException("User not found for username: " + username));
        UserEvent ue = new UserEvent();
        ue.setUser(user);
        ue.setEvent(event);
        return userEventRepository.save(ue);
    }

    @Override
    public void optOut(int eventId, String username) {
        UserEvent userEvent = userEventRepository.findByEvent_IdAndUser_Username(eventId, username)
                .orElseThrow(() -> new EntityNotFoundException("No signups found for event id " + eventId));
        userEventRepository.delete(userEvent);
    }
}
