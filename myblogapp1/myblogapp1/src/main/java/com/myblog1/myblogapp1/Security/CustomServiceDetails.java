package com.myblog1.myblogapp1.Security;

import com.myblog1.myblogapp1.Entity.Role;
import com.myblog1.myblogapp1.Entity.User;
import com.myblog1.myblogapp1.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomServiceDetails implements UserDetailsService {

    private UserRepository userRepository;

    public CustomServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByusernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }



}
