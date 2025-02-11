package org.bromanowski.classbooking.service;

import org.bromanowski.classbooking.repository.MemberRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;
// TODO FIX TESTS

//    @Test
//    void findAllShouldReturnAllMembers() {
//        Member member1 = new Member();
//        Member member2 = new Member();
//        List<Member> members = List.of(member1, member2);
//        when(memberRepository.findAll()).thenReturn(members);
//
//        List<Member> result = memberService.findAll();
//
//        assertEquals(2, result.size());
//        assertEquals(member1, result.get(0));
//        assertEquals(member2, result.get(1));
//    }

//    @Test
//    void findByIdShouldReturnMemberWhenIdExists() {
//        Member member = new Member();
//        member.setId(1);
//        when(memberRepository.findById(1)).thenReturn(Optional.of(member));
//
//        Member result = memberService.findById(1);
//
//        assertEquals(member, result);
//    }

//    @Test
//    void findByIdShouldThrowExceptionWhenIdDoesNotExist() {
//        when(memberRepository.findById(1)).thenReturn(Optional.empty());
//
//        assertThrows(EntityNotFoundException.class, () -> memberService.findById(1));
//    }
//
//    @Test
//    void findByEmailShouldReturnMemberWhenEmailExists() {
//        Member member = new Member();
//        member.setEmail("test@example.com");
//        when(memberRepository.findMemberByEmail("test@example.com")).thenReturn(Optional.of(member));
//
//        Member result = memberService.findByEmail("test@example.com");
//
//        assertEquals(member, result);
//    }
//
//    @Test
//    void findByEmailShouldThrowExceptionWhenEmailDoesNotExist() {
//        when(memberRepository.findMemberByEmail("test@example.com")).thenReturn(Optional.empty());
//
//        assertThrows(EntityNotFoundException.class, () -> memberService.findByEmail("test@example.com"));
//    }

//    @Test
//    void addMemberShouldAddMemberWhenEmailIsUnique() {
//        Member member = new Member();
//        member.setEmail("test@example.com");
//        when(memberRepository.existsByEmail("test@example.com")).thenReturn(false);
//        when(memberRepository.save(any(Member.class))).thenReturn(member); // Return the same member after save
//
//        Member result = memberService.addMember(member);
//
//        assertEquals(member, result);
//        assertNull(member.getId()); // Check if ID is set to null before saving
//        verify(memberRepository, times(1)).save(member); // Verify that save was called
//    }
//
//    @Test
//    void addMemberShouldThrowExceptionWhenEmailExists() {
//        Member member = new Member();
//        member.setEmail("test@example.com");
//        when(memberRepository.existsByEmail("test@example.com")).thenReturn(true);
//
//        assertThrows(EmailExistsException.class, () -> memberService.addMember(member));
//    }

//    @Test
//    void editMemberShouldUpdateMemberWhenIdExists() {
//        int id = 1;
//        Member member = new Member();
//        member.setEmail("test@example.com");
//        when(memberRepository.existsById(id)).thenReturn(true);
//        when(memberRepository.save(any(Member.class))).thenReturn(member);
//
//        Member result = memberService.editMember(id, member);
//
//        assertEquals(member, result);
//        assertEquals(id, member.getId()); // Ensure the ID is set
//        verify(memberRepository, times(1)).save(member); // Verify that save was called
//    }
//
//    @Test
//    void editMemberShouldThrowExceptionWhenIdDoesNotExist() {
//        int id = 1;
//        Member member = new Member();
//        when(memberRepository.existsById(id)).thenReturn(false);
//
//        assertThrows(EntityNotFoundException.class, () -> memberService.editMember(id, member));
//    }

//    @Test
//    void deleteByIdShouldDeleteMemberWhenIdExists() {
//        int id = 1;
//        when(memberRepository.existsById(id)).thenReturn(true);
//
//        memberService.deleteById(id);
//
//        verify(memberRepository, times(1)).deleteById(id);
//    }

//    @Test
//    void deleteByIdShouldThrowExceptionWhenIdDoesNotExist() {
//        int id = 1;
//        when(memberRepository.existsById(id)).thenReturn(false);
//
//        assertThrows(EntityNotFoundException.class, () -> memberService.deleteById(id));
//    }
}