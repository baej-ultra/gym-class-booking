package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.model.MemberEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEventRepository extends JpaRepository<MemberEvent, Integer> {
}
