package com.myblog1.myblogapp1.Controller;

import com.myblog1.myblogapp1.PayLoad.PostDto;
import com.myblog1.myblogapp1.PayLoad.PostResponse;
import com.myblog1.myblogapp1.Service.PostService;
import com.myblog1.myblogapp1.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getAllMapping(@RequestParam(value="pageNo",defaultValue = Constants.DEFAULT_PAGE_NO,required = false) int pageNo, @RequestParam(value="pageSize",defaultValue = Constants.DEFAULT_PAGE_SIZE,required = false)int pageSize, @RequestParam(value = "sortBy",defaultValue =Constants.DEFAULT_SORT_BY,required = false )String sortBy, @RequestParam(value = "sortDir",defaultValue =Constants.DEFAULT_SORT_DIR,required = false )String sortDir){
        PostResponse response = postService.getAllMapping(pageNo, pageSize, sortBy, sortDir);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updateAllPost(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto postDto1 = postService.updateAllPost(postDto, id);
      return  new ResponseEntity<>(postDto1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id){
        postService.deletePostById(id);
       return ResponseEntity.ok("record is deleted!!");
    }
}
