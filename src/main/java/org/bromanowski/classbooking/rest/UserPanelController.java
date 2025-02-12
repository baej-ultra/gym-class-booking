package org.bromanowski.classbooking.rest;

import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.ScheduleEntry;
import org.bromanowski.classbooking.model.User;
import org.bromanowski.classbooking.service.member.MemberService;
import org.bromanowski.classbooking.service.schedule.ScheduleEntryService;
import org.bromanowski.classbooking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel")
public class UserPanelController {

    private final ScheduleEntryService scheduleEntryService;
    private final MemberService memberService;
    private final UserService userService;

    @Autowired
    public UserPanelController(ScheduleEntryService scheduleEntryService, MemberService memberService, UserService userService) {
        this.scheduleEntryService = scheduleEntryService;
        this.memberService = memberService;
        this.userService = userService;
    }

    @GetMapping("/me")
    ResponseEntity<Member> getMemberDetails(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        User user = userService.findByUsername(username);

        return ResponseEntity.ok(user.getMember());
    }

    @GetMapping("/signups")
    ResponseEntity<List<ScheduleEntry>> getSignups(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getSubject();
        int id = userService.findByUsername(username).getId();

        List<ScheduleEntry> events = memberService.getAllEvents(id);
        return ResponseEntity.ok(events);
    }

}
