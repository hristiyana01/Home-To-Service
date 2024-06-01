package controller;

import Backend.hometoservice.controller.PostController;
import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.dto.UpdatePostDto;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.service.ImageService;
import Backend.hometoservice.service.PostService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostControllerTest {

    @InjectMocks
    PostController postController;

    @Mock
    PostService postService;

    @Mock
    ImageService imageService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePost() {
        CreatePostDto createPostDto = new CreatePostDto();
        Post post = new Post();
        when(postService.createPost(any(CreatePostDto.class))).thenReturn(post);

        ResponseEntity<Post> response = postController.createPost(createPostDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(post, response.getBody());
    }

    @Test
    public void testUpdatePost() throws NotFoundException {
        UpdatePostDto updatePostDto = new UpdatePostDto();
        Post post = new Post();
        when(postService.updatePost(anyInt(), any(UpdatePostDto.class))).thenReturn(post);

        ResponseEntity<Post> response = postController.updatePost(1, updatePostDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(post, response.getBody());
    }

    // Continue with other methods...
}