package org.bromanowski.classbooking.rest;

import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.dto.MemberDto;
import org.bromanowski.classbooking.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    ResponseEntity<List<MemberDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/id/{id}")
    ResponseEntity<MemberDto> getMemberById(@PathVariable int id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping("/email/{email}")
    ResponseEntity<MemberDto> getMemberByEmail(@PathVariable String email) {
        MemberDto memberDto = memberService.findByEmail(email);
        return ResponseEntity.ok(memberDto);
    }

    @GetMapping("/{id}/schedule")
    ResponseEntity<List<ScheduleEntry>> getEventsForMember(@PathVariable int id,
                                                           @RequestParam(value = "week", required = false) Integer week) {
        // Get current week if not specified
        if (week == null) {
            LocalDate localDate = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            week = localDate.get(weekFields.weekOfYear());
        }
        List<ScheduleEntry> events = memberService.getEventsByWeek(id, week);
        return ResponseEntity.ok(events);
    }

//    @PostMapping
//    ResponseEntity<Member> addMember(@RequestBody @Valid Member member) {
//        Member newMember = memberService.addMember(member);
//        return ResponseEntity.ok(newMember);
//    }

//    @PutMapping("id/{id}")
//    ResponseEntity<Member> editMember(@PathVariable int id, @RequestBody @Valid Member member) {
//        Member editedMember = memberService.addMember(member);
//        return ResponseEntity.ok(editedMember);
//    }

//    @DeleteMapping("/{id}")
//    ResponseEntity<Void> deleteMember(@PathVariable int id) {
//        memberService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}