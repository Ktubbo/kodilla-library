package com.library.service;

import com.library.user.domain.User;
import com.library.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DBService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() { return userRepository.findAll(); }
    public Optional<User> getUserById(Long id) { return userRepository.findById(id); }
    public User saveUser(final User user) { return userRepository.save(user); }
    public void deleteUser(Long id) { userRepository.deleteById(id);}

}