package com.thinkify.eventCRUD.Configuration;

import com.thinkify.eventCRUD.Model.UserInfo;
import com.thinkify.eventCRUD.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class userInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private UserInfoUserDetails userInfoUserDetails;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo =userRepository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found" + username));
    }
}
