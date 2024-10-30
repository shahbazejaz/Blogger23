package com.myblog1.myblogapp1.Service.Impl;

import com.myblog1.myblogapp1.Entity.Comment;
import com.myblog1.myblogapp1.Entity.Post;
import com.myblog1.myblogapp1.Exception.ResourceNotFoundException;
import com.myblog1.myblogapp1.PayLoad.CommentDto;
import com.myblog1.myblogapp1.Repository.CommentRepository;
import com.myblog1.myblogapp1.Repository.PostRepository;
import com.myblog1.myblogapp1.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepo;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = mapToComment(commentDto);
        comment.setPost(post);
        Comment comment1 = commentRepository.save(comment);
        CommentDto commentDto1 = mapToCommentDto(comment1);
        return commentDto1;
    }

    @Override
    public List<CommentDto> getComments(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> dtos = comments.stream().map(c -> mapToCommentDto(c)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public CommentDto updateComments(long postId, CommentDto commentDto, long id) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        Comment comment1 = commentRepository.save(comment);
        CommentDto dto = mapToCommentDto(comment1);
        return dto;
    }

    @Override
    public void deleteComments(long postId, long id) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        commentRepository.deleteById(id);
    }

    public Comment mapToComment(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment=new Comment();
//        comment.setName(commentDto.getName());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
        return comment;
    }
    public CommentDto mapToCommentDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        CommentDto commentDto=new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setBody(comment.getBody());
//        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }
}
