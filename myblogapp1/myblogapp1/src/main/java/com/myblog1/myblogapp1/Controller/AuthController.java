package com.myblog1.myblogapp1.Controller;

import com.myblog1.myblogapp1.Entity.Role;
import com.myblog1.myblogapp1.Entity.User;
import com.myblog1.myblogapp1.PayLoad.JWTAuthResponse;
import com.myblog1.myblogapp1.PayLoad.LoginDto;
import com.myblog1.myblogapp1.PayLoad.SignUpDto;
import com.myblog1.myblogapp1.Repository.RoleRepository;
import com.myblog1.myblogapp1.Repository.UserRepository;
import com.myblog1.myblogapp1.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthResponse> authenticationUser(@RequestBody LoginDto loginDto){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = tokenProvider.generateToken(authenticate);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUse(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByusername(signUpDto.getUsername())){
            return new ResponseEntity<>("UserName is already taken!",HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!",HttpStatus.BAD_REQUEST);
        }
        User user=new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role=roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);
        return new ResponseEntity<>("User registered Succesfully",HttpStatus.OK);
    }
}
