package app.services;

import app.dtos.post.CreatePostDto;
import app.dtos.post.PostDto;
import app.dtos.post.UpdatePostDto;
import app.models.Post;
import javassist.NotFoundException;

import java.util.List;

public interface PostService {
    Post createPost(CreatePostDto createPostDto);

    List<PostDto> getUserPosts(Integer userId);

    List<Post> getUserFavoritePosts(Integer userId);

    Post updatePost(Integer postId, UpdatePostDto updatePostDto) throws NotFoundException;

    List<PostDto> findAllPosts();

    List<PostDto> findAllPostsByCategoryId(Integer categoryId);
}
