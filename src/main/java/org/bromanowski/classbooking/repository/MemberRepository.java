package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
