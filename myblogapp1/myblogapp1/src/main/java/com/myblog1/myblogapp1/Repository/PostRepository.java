package com.myblog1.myblogapp1.Repository;

import com.myblog1.myblogapp1.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
