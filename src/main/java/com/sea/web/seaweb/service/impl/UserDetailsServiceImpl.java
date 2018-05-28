package com.sea.web.seaweb.service.impl;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.model.UserDetailsImpl;
import com.sea.web.seaweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findByEmail(email);
        return opt.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException("Baaad credentials"));
    }
}
