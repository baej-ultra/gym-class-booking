package org.bromanowski.classbooking.service.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.User;
import org.bromanowski.classbooking.model.dto.NewUserDto;
import org.bromanowski.classbooking.repository.MemberRepository;
import org.bromanowski.classbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MemberRepository memberRepository, PasswordEncoder passwordEncoder, UserDetailsManager userDetailsManager) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found for id " + id));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new EntityNotFoundException("User not found for username: " + username));
    }

    @Override
    public User addUser(NewUserDto newUser) {
        UserDetails user = org.springframework.security.core.userdetails.User
                .withUsername(newUser.username())
                .password(newUser.password())
                .passwordEncoder(passwordEncoder::encode)
                .roles("MEMBER")
                .build();
        userDetailsManager.createUser(user);

        User savedUser = findByUsername(newUser.username());
        Member member = new Member();
        member.setFirstName(newUser.firstName());
        member.setLastName(newUser.lastName());
        member.setEmail(newUser.email());
        member.setUser(savedUser);
        Member savedMember = memberRepository.save(member);

        savedUser.setMember(savedMember);
        return savedUser;
    }

    @Override
    public Member editUserDetails(int id, Member newDetails) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found for id " + id);
        }
        newDetails.setId(id);
        return memberRepository.save(newDetails);
    }

    @Override
    public void changeUserPassword(int id, String password) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found for id " + id));
        String encodedPassword = passwordEncoder.encode(password);

        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found for id " + id);
        }
//        userRepository.deleteById(id);
        String username = findById(id).getUsername();
        userDetailsManager.deleteUser(username);
    }
}

