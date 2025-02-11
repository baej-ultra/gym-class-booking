package org.bromanowski.classbooking.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.bromanowski.classbooking.model.Member;
import org.bromanowski.classbooking.model.NewUserDto;
import org.bromanowski.classbooking.model.User;
import org.bromanowski.classbooking.repository.MemberRepository;
import org.bromanowski.classbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, InMemoryUserDetailsManager user, MemberRepository memberRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
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
    public User addUser(NewUserDto newUser) {
        User user = new User();
        user.setUsername(newUser.username());
        user.setPassword(newUser.password());
        user.setId(null);
        User savedUser = userRepository.save(user);

        Member member = new Member();
        member.setId(savedUser.getId());
        member.setFirstName(newUser.firstName());
        member.setLastName(newUser.lastName());
        member.setEmail(newUser.email());

        memberRepository.save(member);
        savedUser.setMember(member);

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
    public User changeUserPassword(int id, String password) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found for id " + id));
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found for id " + id);
        }
        userRepository.deleteById(id);
    }
}

