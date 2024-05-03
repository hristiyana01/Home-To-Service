package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.dto.UpdatePostDto;
import Backend.hometoservice.model.Favourites;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.model.User;
import Backend.hometoservice.repository.PostRepository;
import Backend.hometoservice.repository.UserRepository;
import Backend.hometoservice.service.PostService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public Post createPost(CreatePostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .status(postDto.getStatus())
                .categoryId(postDto.getCategoryId())
                .createdDate(Instant.now())
                .location(postDto.getLocation())
                .userId(postDto.getUserId())
                .phoneNumber(postDto.getPhoneNumber())
                .build();
       return postRepository.save(post);
    }

    @Override
    public List<CreatePostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToCreatePostDto(post)).collect(Collectors.toList());
    }

    private CreatePostDto mapToCreatePostDto(Post post) {
        CreatePostDto postDto = CreatePostDto.builder()
                .title(post.getTitle())
                .description(post.getDescription())
                .categoryId(post.getCategoryId())
                .userId(post.getUserId())
                .location(post.getLocation())
                .status(post.getStatus())
                .build();
        return postDto;
    }
    //
//    @Override
//    public List<Post> findAllPosts() {
//        return postRepository.findAll();
//
//    }
    @Override
    public List<Post> getUserPosts(Integer userId) {
        Optional<List<Post>> userPostsOptional = postRepository.findPostsByUserId(userId);
        if(userPostsOptional.isEmpty()) {
            return new ArrayList<Post>();
        }

        return userPostsOptional.get();
    }

    @Override
    public List<Post> getUserFavoritePosts(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("User not found by the provided id.");
        }
        List<Favourites> favourites = user.get().getFavourites();
        if(favourites.isEmpty()){
            return new ArrayList<Post>();
        }
        List<Integer> postIds = favourites.stream()
                .map(Favourites::getPostId)
                .collect(Collectors.toList());

        return postRepository.findAllById(postIds);
    }

    @Override
    public Post updatePost(Integer postId, UpdatePostDto updatePostDto) throws NotFoundException {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isEmpty()) {
            throw new NotFoundException("Post with id " + postId +" not found.");
        }
        Post post = optionalPost.get();

        post.setStatus(updatePostDto.getStatus()!= null ? updatePostDto.getStatus() : post.getStatus());
        post.setTitle(updatePostDto.getTitle()!= null ? updatePostDto.getTitle() : post.getTitle());
        post.setDescription(updatePostDto.getContent() != null ? updatePostDto.getContent() : post.getDescription());
        post.setPrice(updatePostDto.getPrice() != null ? updatePostDto.getPrice() : post.getPrice());

        post.setUpdatedDate(Instant.now());
        return postRepository.save(post);


    }

    @Override
    public Optional<List<Post>> findAllPostsByCategoryId(Integer categoryId) {
        Optional<List<Post>> posts = postRepository.findPostsByCategoryId(categoryId);
        return posts;
    }



}
