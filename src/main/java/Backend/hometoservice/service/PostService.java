package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.model.Post;

public interface PostService {
    Post createPost(CreatePostDto createPostDto);
}
