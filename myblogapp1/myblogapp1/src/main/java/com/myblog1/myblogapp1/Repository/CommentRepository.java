package com.myblog1.myblogapp1.Repository;

import com.myblog1.myblogapp1.Entity.Comment;
import com.myblog1.myblogapp1.PayLoad.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);
}
