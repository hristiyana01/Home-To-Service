package app.services.implementations;

import app.dtos.post.CreatePostDto;
import app.dtos.post.PostDto;
import app.dtos.post.UpdatePostDto;
import app.enums.Status;
import app.models.Category;
import app.models.Image;
import app.models.Post;
import app.repositories.CategoryRepository;
import app.repositories.ImageRepository;
import app.repositories.PostRepository;
import app.repositories.UserRepository;
import app.services.PostService;
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
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Post createPost(CreatePostDto createPostDto) {
        var user = userRepository.findById(createPostDto.getUserId());

        if (user.isEmpty()) {
            return null;
        }

        Post post = Post.builder()
                .title(createPostDto.getTitle())
                .description(createPostDto.getDescription())
                .price(createPostDto.getPrice())
                .status(Status.ACTIVE)
                .categoryId(createPostDto.getCategoryId())
                .createdDate(Instant.now())
                .location(createPostDto.getLocation())
                .user(user.get())
                .createdDate(Instant.now())
                .updatedDate(Instant.now())
                .build();

        return postRepository.save(post);
    }

    public List<PostDto> getUserPosts(Integer userId) {
        List<Post> posts = postRepository.findPostsByUserId(userId).get();
        return posts.stream()
                .map(this::mapToPostDto)
                .collect(Collectors.toList());
    }

    private PostDto mapToPostDto(Post post) {
        List<String> imageFilePaths = imageRepository.findAllByPostId(post.getId())
                .stream()
                .map(Image::getFilePath)
                .collect(Collectors.toList());

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setPrice(post.getPrice());
        postDto.setCreatedDate(post.getCreatedDate());
        postDto.setLocation(post.getLocation());
        postDto.setImage(imageFilePaths.getFirst());

        return postDto;
    }


    public List<PostDto> getUserFavoritePosts(Integer userId) {
        var posts = postRepository.findFavoritePostsByUserId(userId);
        List<PostDto> postdtos = new ArrayList<>();

        for (int i = 0; i < posts.size(); i++) {
            var postdto = mapToDto(posts.get(i));
            List<String> imageFilePaths = imageRepository.findAllByPostId(postdto.getId())
                    .stream()
                    .map(Image::getFilePath)
                    .collect(Collectors.toList());
            postdto.setImage(imageFilePaths.getFirst());

            postdtos.add((postdto));
        }
        return postdtos;
    }

    private PostDto mapToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .location(post.getLocation())
                .createdDate(post.getCreatedDate())
                .price(post.getPrice())
                .build();
    }

    @Override
    public Post updatePost(Integer postId, UpdatePostDto updatePostDto) throws NotFoundException {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new NotFoundException("Post not found with id: " + postId);
        }

        Post post = postOptional.get();

        // Update the post fields with the values from updatePostDto
        if (updatePostDto.getTitle() != null) {
            post.setTitle(updatePostDto.getTitle());
        }

        if (updatePostDto.getDescription() != null) {
            post.setDescription(updatePostDto.getDescription());
        }

        if (updatePostDto.getPrice() != null) {
            post.setPrice(updatePostDto.getPrice());
        }

        if (updatePostDto.getStatus() != null) {
            post.setStatus(Status.valueOf(updatePostDto.getStatus().toUpperCase()));
        }

        if (updatePostDto.getLocation() != null) {
            post.setLocation(updatePostDto.getLocation());
        }

        post.setCategoryId(Integer.parseInt(updatePostDto.getCategoryId()));

        // Save the updated post
        return postRepository.save(post);
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();

        for (Post post : posts) {
            var images = imageRepository.findAllByPostId(post.getId());
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .price(post.getPrice())
                    .createdDate(post.getCreatedDate())
                    .location(post.getLocation())
                    .image(images.get(0).getFilePath())
                    .build();

            postDtos.add(postDto);
        }

        return postDtos;
    }


    @Override
    public List<PostDto> findAllPostsByCategoryId(Integer categoryId) {
       var posts = postRepository.findAllByCategoryId(categoryId).get();
        List<PostDto> postDtos = new ArrayList<>();

        for (Post post : posts) {
            var images = imageRepository.findAllByPostId(post.getId());
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .price(post.getPrice())
                    .createdDate(post.getCreatedDate())
                    .location(post.getLocation())
                    .image(images.get(0).getFilePath())
                    .build();

            postDtos.add(postDto);
        }

        return postDtos;
    }
}
