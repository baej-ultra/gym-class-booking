package org.bromanowski.classbooking.service;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.exception.EmailExistsException;
import org.bromanowski.classbooking.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(int id) {
        var memberOptional = memberRepository.findById(id);
        return memberOptional.orElseThrow(() ->
                new EntityNotFoundException("Member not found for id " + id));
    }

    @Override
    public Member findByEmail(String email) {
        var memberOptional = memberRepository.findMemberByEmail(email);
        return memberOptional.orElseThrow(() ->
                new EntityNotFoundException("Member not found for email " + email));
    }

    @Override
    public Member addMember(Member member) {
        String email = member.getEmail();
        if (memberRepository.existsByEmail(email)) {
            throw new EmailExistsException("Email %s already in database".formatted(email));
        }
        member.setId(null);
        return memberRepository.save(member);
    }

    @Override
    public Member editMember(int id, Member member) {
        checkIfExistsById(id);
        member.setId(id);
        return memberRepository.save(member);
    }

    @Override
    public void deleteById(int id) {
        checkIfExistsById(id);
        memberRepository.deleteById(id);
    }

    @Override
    public List<ScheduleEntry> getEvents(int id, int week) {
        return memberRepository.findEventsByWeekForMember(id, week);
    }

    private void checkIfExistsById(int id) {
        if (!memberRepository.existsById(id)) {
            throw new EntityNotFoundException("Member not found for id " + id);
        }
    }
}
