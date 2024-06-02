package Backend.hometoservice.service.implementation;

import Backend.hometoservice.dto.CreateCommentDto;
import Backend.hometoservice.dto.CreatePostDto;
import Backend.hometoservice.dto.detailedPost.DetailedPostDto;
import Backend.hometoservice.dto.detailedPost.DetailedPostResponseDto;
import Backend.hometoservice.dto.PostDto;
import Backend.hometoservice.dto.UpdatePostDto;
import Backend.hometoservice.dto.detailedPost.PostCommentDto;
import Backend.hometoservice.dto.detailedPost.SellerDto;
import Backend.hometoservice.model.*;
import Backend.hometoservice.repository.CommentRepository;
import Backend.hometoservice.repository.ImageRepository;
import Backend.hometoservice.repository.PostRepository;
import Backend.hometoservice.repository.UserRepository;
import Backend.hometoservice.service.PostService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;

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
                .images(imageRepository.findAll())
                .createdDate(Instant.now())
                .build();

//        if (imageFile != null && !imageFile.isEmpty()) {
//            byte[] bytes = imageFile.getBytes();
//            post.setImage(bytes);
//        }

       return postRepository.save(post);
    }

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
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());
    }

    private PostDto mapToPostDto(Post post) {
        PostDto postDto = PostDto.builder()
                .title(post.getTitle())
                .description(post.getDescription())
                .categoryId(post.getCategoryId())
                .userId(post.getUserId())
                .location(post.getLocation())
                .status(post.getStatus())
                .id(post.getId())
                .createdDate(post.getCreatedDate())
                .build();
        return postDto;
    }

    @Override
    public List<Post> findAllPostsByCategoryId(Integer categoryId) {
        Optional<List<Post>> posts = postRepository.findPostsByCategoryId(categoryId);
        return posts.orElseGet(ArrayList::new);
    }

    @Override
    public DetailedPostResponseDto getDetailedPostData(Integer postId) throws NotFoundException {
        Optional<Post> postOpt = postRepository.findById(postId);
        if(postOpt.isEmpty()) {
            throw new NotFoundException("Post with id " + postId + " not found.");
        }
        var post = postOpt.get();

        List<Comment> comments = commentRepository.findAllByPostId(postId);
        List<Integer> allCommentsUsers = comments.stream().map(c -> c.getUserId())
                .distinct().collect(Collectors.toList());

        List<User> users = userRepository.findAllById(allCommentsUsers);

        Map<Integer, String> usernamesById = users.stream()
                .collect(Collectors.toMap(User::getId, User::getUsername));

        List<PostCommentDto> commentDtos = comments.stream()
                .map(c -> mapToPostCommentDto(c, usernamesById))
                .collect(Collectors.toList());
        List<Image> images = imageRepository.findAllByPostId(postId);

        User user = userRepository.findById(post.getUserId()).orElseThrow(() -> new NotFoundException("User with id " + post.getUserId() + " not found."));
        SellerDto sellerDto = mapToSellerDto(user);


        DetailedPostResponseDto detailedPostResponseDto = new DetailedPostResponseDto();
        var detailedPost = new DetailedPostDto();
        detailedPost.setId(post.getId());
        detailedPost.setDescription(post.getDescription());
        detailedPost.setLocation(post.getLocation());
        detailedPost.setStatus(post.getStatus());
        detailedPost.setLocation(post.getLocation());
        detailedPost.setPhoneNumber(post.getPhoneNumber());
        detailedPost.setCategoryId(post.getCategoryId());
        detailedPost.setTitle(post.getTitle());
        detailedPost.setUserId(post.getUserId());
        detailedPost.setPrice(post.getPrice());

        detailedPostResponseDto.setPost(detailedPost);
        detailedPostResponseDto.setComments(commentDtos);
        detailedPostResponseDto.setImages(images);
        detailedPostResponseDto.setUser(sellerDto);

      //      detailedPostResponseDto.setComments(detailedPo);
        //it's u now Hrisi u got this, moga i az ppc ako kajesh

        //kum tova api trqbva da dobavim
        //commentari
        //reviews
        //oshte kakvoto iskash za detailnoto view za posta

        //nov obekt koito da izkarva vajnata informaciq za post-a ->DetailedPOstDto
        // -> koi potrebitel q e suzdal naprimer osven ako ne e protiv GDPR

        //tochno taka ama v tozi service tuk deto pisha v momenta trqbva da se sluchi tova
        // slivane, za da se vurne cqlata informaciq kum FrontEnda
        // moje bi direktno repotata , ne servizite. Kato cqlo toq pattern koito polzvash -> service->repo e legacy i e obsolete i ne se polzva veche


        //1. suzdai si purvo modela koito iskash da vrushtash kato danni kum frontenda
        //2. namapni dannite ot posta
        //3. dobavi dannite za suzdatelq na posta
        //4. dobavi dannite za reviews
        //5. dobavi commentarite kum posta
        //6. kvoto dr ima  go dobawi ne ti  tablicite gi imash prosto trqbva da gi sleesh v 1 rezultat
        return detailedPostResponseDto;
    }

    private PostCommentDto mapToPostCommentDto(Comment comment, Map<Integer, String> usernamesById) {
        return PostCommentDto.builder()
                .id(comment.getId())
                .text(comment.getCommentText())
                .userId(comment.getUserId())
                .username(usernamesById.get(comment.getUserId()))
                .commentDate(comment.getCreatedAt())
                .build();
    }

    private SellerDto mapToSellerDto(User user) {
        return SellerDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .country(user.getCountry())
                .build();
    }
}
