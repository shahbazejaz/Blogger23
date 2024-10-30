package com.myblog1.myblogapp1.Controller;

import com.myblog1.myblogapp1.PayLoad.CommentDto;
import com.myblog1.myblogapp1.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId")long postId, @RequestBody CommentDto commentDto){
        CommentDto comment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getComment(@PathVariable("postId")long postId){
        List<CommentDto> comments = commentService.getComments(postId);
        return comments;
    }
    @PutMapping("posts/{postId}/comments/{Id}")
    public ResponseEntity<CommentDto> updateComments(@PathVariable("postId")long postId,@PathVariable("Id")long Id,@RequestBody CommentDto commentDto){
        CommentDto commentDto1 = commentService.updateComments(postId, commentDto, Id);
        return new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/comments/{Id}")
    public ResponseEntity<String> deleteComments(@PathVariable("postId")long postId,@PathVariable("Id")long Id){
        commentService.deleteComments(postId,Id);
        return new ResponseEntity<>("comment deleted successfully",HttpStatus.OK);
    }
}
