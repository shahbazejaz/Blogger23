package com.myblog1.myblogapp1.Service;

import com.myblog1.myblogapp1.PayLoad.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

   List<CommentDto> getComments(long postId);

   CommentDto updateComments(long postId, CommentDto commentDto, long id);

    void deleteComments(long postId, long id);
}
