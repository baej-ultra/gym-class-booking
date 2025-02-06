package org.bromanowski.classbooking.repository;

import org.bromanowski.classbooking.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findMemberByEmail(String email);

    Boolean existsByEmail(String email);
}
