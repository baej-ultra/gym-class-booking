package org.bromanowski.classbooking.rest;

import org.bromanowski.classbooking.entity.Member;
import org.bromanowski.classbooking.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    List<Member> getAllMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    Member getMemberById(@PathVariable int id) {
        return memberService.findById(id);
    }

    @PostMapping
    Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @PutMapping("/{id}")
    Member editMember(@PathVariable int id, @RequestBody Member member) {
        return memberService.editMember(id, member);
    }

    @DeleteMapping("/{id}")
    String deleteMember(@PathVariable int id) {
        memberService.deleteById(id);
        return "Deleted user with id " + id;
    }
}
