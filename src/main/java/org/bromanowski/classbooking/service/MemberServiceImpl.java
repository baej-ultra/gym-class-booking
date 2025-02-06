package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.entity.Event;
import org.bromanowski.classbooking.entity.Member;
import org.bromanowski.classbooking.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService{

    MemberRepository memberRepository;

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
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found for id " + id));
    }

    @Override
    public Member addMember(Member member) {
        member.setId(null);
        return memberRepository.save(member);
    }

    @Override
    public Member editMember(int id, Member member) {
        memberRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found for id " + id));
        member.setId(id);
        return memberRepository.save(member);
    }

    @Override
    public void deleteById(int id) {
        memberRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found for id " + id));
        memberRepository.deleteById(id);
    }

    @Override
    public Set<Event> getEvents(int id) {
        //TODO when EventRepository
        return null;
    }
}
