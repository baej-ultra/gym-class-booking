package org.bromanowski.classbooking.repository;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.bromanowski.classbooking.model.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEventRepository extends JpaRepository<UserEvent, Integer> {

    List<UserEvent> findByEvent_IdAndUser_Id(Integer eventId, Integer userId);

    Optional<UserEvent> findByEvent_IdAndUser_Username(Integer eventId, String userUsername);

    int countUserEventsByEvent_Id(Integer eventId);

    boolean existsByUser_IdAndEvent_Id(Integer userId, Integer eventId);

    boolean existsByUser_UsernameAndEvent_Id(@Size(max = 100) @NotNull String userUsername, Integer eventId);

    boolean existsByEvent_IdAndUser_Username(Integer eventId, @Size(max = 100) @NotNull String userUsername);
}
