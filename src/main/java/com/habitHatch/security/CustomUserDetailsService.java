package com.habitHatch.security;

import com.habitHatch.db.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        try{
            if (userId == null || userId.isEmpty()) {
                throw new UsernameNotFoundException("User ID cannot be null or empty");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error occurred while loading user by username: " + e.getMessage());
        }
        Users user = userDetailsRepository.findByUserId(userId);
        return new CustomUserDetails(user);
    }
}
