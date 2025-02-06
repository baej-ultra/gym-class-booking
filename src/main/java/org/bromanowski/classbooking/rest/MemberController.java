package org.bromanowski.classbooking.rest;

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

    @GetMapping("/{id}")
    ResponseEntity<Member> getMemberById(@PathVariable int id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    //TODO GET for all events for current member
    // - filer by date

    @PostMapping
    ResponseEntity<Member> addMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.addMember(member));
    }

    @PutMapping("/{id}")
    ResponseEntity<Member> editMember(@PathVariable int id, @RequestBody Member member) {
        return ResponseEntity.ok(memberService.editMember(id, member));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMember(@PathVariable int id) {
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
