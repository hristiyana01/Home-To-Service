package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.repository.PostRepository;
import Backend.hometoservice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;
    public Post createPost(CreatePostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .status(postDto.getStatus())
                .categoryId(postDto.getCategoryId())
                .createdDate(Instant.now())
                .location(postDto.getLocation())
                .user_creator(postDto.getUserCreator())
                .phoneNumber(postDto.getPhoneNumber())
                .build();
       return postRepository.save(post);
    }
}
