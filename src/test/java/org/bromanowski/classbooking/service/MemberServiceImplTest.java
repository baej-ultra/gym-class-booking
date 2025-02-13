package org.bromanowski.classbooking.service;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.dto.MemberDto;
import org.bromanowski.classbooking.model.mapper.MemberMapper;
import org.bromanowski.classbooking.repository.MemberRepository;
import org.bromanowski.classbooking.service.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void findAll_shouldReturnMappedMembers() {
        // GIVEN
        Member member = new Member();
        member.setFirstName("John");
        MemberDto dto = new MemberDto("John", null, null);

        List<Member> members = List.of(member);
        when(memberRepository.findAll()).thenReturn(members);
        when(memberMapper.toDto(member)).thenReturn(dto);

        // WHEN
        List<MemberDto> result = memberService.findAll();

        // THEN
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).firstName());
        verify(memberRepository).findAll();
        verify(memberMapper).toDto(member);
    }

    @Test
    void findById_shouldReturnMember_whenIdExists() {
        Member member = new Member();
        MemberDto dto = new MemberDto(null, null, null);

        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        when(memberMapper.toDto(member)).thenReturn(dto);

        MemberDto result = memberService.findById(1);

        assertEquals(dto, result);
        verify(memberRepository).findById(1);
        verify(memberMapper).toDto(member);
    }

    @Test
    void findById_shouldThrowException_whenIdDoesNotExist() {
        when(memberRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> memberService.findById(1));
    }

    @Test
    void findByEmail_shouldReturnMember_whenEmailExists() {
        Member member = new Member();
        member.setEmail("test@example.com");
        MemberDto memberDto = new MemberDto(null, null, "test@example.com");

        when(memberRepository.findMemberByEmail(any())).thenReturn(Optional.of(member));
        when(memberMapper.toDto(any())).thenReturn(memberDto);

        MemberDto result = memberService.findByEmail("test@example.com");

        assertEquals(memberDto, result);
        verify(memberRepository).findMemberByEmail("test@example.com");
        verify(memberMapper).toDto(member);
    }

    @Test
    void findByEmail_shouldThrowException_whenEmailDoesNotExist() {
        when(memberRepository.findMemberByEmail("test@example.com")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> memberService.findByEmail("test@example.com"));
    }
}