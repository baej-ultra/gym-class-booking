package org.bromanowski.classbooking.service.member;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.model.mapper.MemberMapper;
import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.dto.MemberDto;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public List<MemberDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(memberMapper::toDto)
                .toList();
    }

    @Override
    public MemberDto findById(int id) {
        var memberOptional = memberRepository.findById(id);
        Member member = memberOptional.orElseThrow(() ->
                new EntityNotFoundException("Member not found for id " + id));
        return memberMapper.toDto(member);
    }

    @Override
    public MemberDto findByEmail(String email) {
        var memberOptional = memberRepository.findMemberByEmail(email);
        Member member = memberOptional.orElseThrow(() ->
                new EntityNotFoundException("Member not found for email " + email));
        return memberMapper.toDto(member);
    }

//    @Override
//    public Member addMember(Member member) {
//        String email = member.getUsername();
//        if (memberRepository.existsByEmail(email)) {
//            throw new EmailExistsException("Email %s already in database".formatted(email));
//        }
//        member.setId(null);
//        return memberRepository.save(member);
//    }

//    @Override
//    public Member editMember(int id, MemberDto memberDto) {
//        checkIfExistsById(id);
//        Member member = memberMapper.toEntity(memberDto);
//        member.setId(id);
//        return memberRepository.save(member);
//    }

//    @Override
//    public void deleteById(int id) {
//        checkIfExistsById(id);
//        memberRepository.deleteById(id);
//    }

    @Override
    public List<ScheduleEntry> getEventsByWeek(int id, int week) {
        return memberRepository.findEventsByWeekForMember(id, week);
    }

    @Override
    public List<ScheduleEntry> getAllEvents(int id) {
        return memberRepository.findEventsForMember(id);
    }

    private void checkIfExistsById(int id) {
        if (!memberRepository.existsById(id)) {
            throw new EntityNotFoundException("Member not found for id " + id);
        }
    }
}
