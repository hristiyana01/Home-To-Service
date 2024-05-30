package Backend.hometoservice.service;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.dto.detailedPost.DetailedPostResponseDto;
import Backend.hometoservice.dto.PostDto;
import Backend.hometoservice.dto.UpdatePostDto;
import Backend.hometoservice.model.Post;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(CreatePostDto createPostDto);
    List<Post> getUserPosts(Integer userId);
    List<Post> getUserFavoritePosts(Integer userId);

    Post updatePost(Integer postId, UpdatePostDto updatePostDto) throws NotFoundException;

    List<PostDto> findAllPosts();

    List<Post> findAllPostsByCategoryId(Integer categoryId);
    DetailedPostResponseDto getDetailedPostData(Integer postId) throws NotFoundException;
}
