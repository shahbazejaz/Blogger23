package com.myblog1.myblogapp1.Service;

import com.myblog1.myblogapp1.PayLoad.PostDto;
import com.myblog1.myblogapp1.PayLoad.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

   PostResponse getAllMapping(int pageNo, int pageSize, String sortBy, String sortDir);


    PostDto getPostById(long id);



  PostDto updateAllPost(PostDto postDto, long id);

    void deletePostById(long id);
}
