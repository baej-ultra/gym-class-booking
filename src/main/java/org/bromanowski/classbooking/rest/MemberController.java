package org.bromanowski.classbooking.rest;

import jakarta.validation.Valid;
import org.bromanowski.classbooking.entity.Member;
import org.bromanowski.classbooking.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {

    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/id/{id}")
    ResponseEntity<Member> getMemberById(@PathVariable int id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping("/email/{email}")
    ResponseEntity<Member> getMemberByEmail(@PathVariable String email) {
        Member member = memberService.findByEmail(email);
        return ResponseEntity.ok(member);
    }

    //TODO GET for all events for current member
    // - filer by date

    @PostMapping
    ResponseEntity<Member> addMember(@RequestBody @Valid Member member) {
        Member newMember = memberService.addMember(member);
        return ResponseEntity.ok(newMember);
    }

    @PutMapping("id/{id}")
    ResponseEntity<Member> editMember(@PathVariable int id, @RequestBody @Valid Member member) {
        Member editedMember = memberService.addMember(member);
        return ResponseEntity.ok(editedMember);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMember(@PathVariable int id) {
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}