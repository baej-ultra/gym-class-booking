package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEventRepository extends JpaRepository<UserEvent, Integer> {

    List<UserEvent> findByEvent_IdAndUser_Id(Integer eventId, Integer userId);

    Optional<UserEvent> findByEvent_IdAndUser_Username(Integer eventId, String userUsername);
}
