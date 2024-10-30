package com.myblog1.myblogapp1.Service.Impl;

import com.myblog1.myblogapp1.Entity.Post;
import com.myblog1.myblogapp1.Exception.ResourceNotFoundException;
import com.myblog1.myblogapp1.PayLoad.PostDto;
import com.myblog1.myblogapp1.PayLoad.PostResponse;
import com.myblog1.myblogapp1.Repository.PostRepository;
import com.myblog1.myblogapp1.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;
   private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=mapToEntity(postDto);
        Post postEntity = postRepo.save(post);
        PostDto dto= mapToDto(postEntity);
        return dto;

    }

    @Override
    public PostResponse getAllMapping(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest request = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepo.findAll(request);
        List<Post> post = posts.getContent();
        List<PostDto> postdtos = posts.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postdtos);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPage(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;

    }

    @Override
    public PostDto getPostById(long id) {
       Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
       PostDto postDto = mapToDto(post);
        return postDto;
    }

    @Override
    public PostDto updateAllPost(PostDto postDto, long id) {
        Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post post1 = postRepo.save(post);
        PostDto dto = mapToDto(post1);
        return dto;
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        postRepo.deleteById(id);
    }


    public Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
    public PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
//        PostDto dto=new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
        return dto;
    }
}
