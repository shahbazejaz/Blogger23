package com.myblog1.myblogapp1.Repository;

import com.myblog1.myblogapp1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByusername(String username);
    Optional<User> findByusernameOrEmail(String username,String email);
    Optional<User> findByEmail(String email);
    boolean existsByusername(String username);
    boolean existsByEmail(String email);


}
