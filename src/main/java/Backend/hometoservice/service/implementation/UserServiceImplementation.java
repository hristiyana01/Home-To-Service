package Backend.hometoservice.service.implementation;

//import Backend.hometoservice.authorization.dto.ERole;
import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.dto.PostDto;
import Backend.hometoservice.dto.UserDto;
//import Backend.hometoservice.model.Role;
import Backend.hometoservice.dto.detailedPost.DetailedPostDto;
import Backend.hometoservice.dto.detailedPost.DetailedPostResponseDto;
import Backend.hometoservice.dto.detailedUserDto.DetailedUserDto;
import Backend.hometoservice.dto.detailedUserDto.DetailedUserResponseDto;
import Backend.hometoservice.enums.Status;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.model.Review;
import Backend.hometoservice.model.User;
//import Backend.hometoservice.repository.RolesRepository;
import Backend.hometoservice.repository.PostRepository;
import Backend.hometoservice.repository.ReviewRepository;
import Backend.hometoservice.repository.UserRepository;
import Backend.hometoservice.service.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
   // private final RolesRepository rolesRepository;
    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;
    @Override
    public User createUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getUsername())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .country(userDto.getCountry())
                .city(userDto.getCity())
                .zip(userDto.getZip())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .location(userDto.getLocation())
                .registeredDate(Instant.now())
                .updatedDate(Instant.now())
                .build();

//        var userRole = rolesRepository.findByName(ERole.ROLE_USER);
//        var userRoles = new HashSet<Role>();
//        userRoles.add(userRole.get());
//        user.setRoles(userRoles);
//        userRepository.save(user);
        return user;
    }

//    @Override
//    public User updateUserEmail(Integer id, String email) {
//       Optional<User> user = userRepository.findById(id);
//       if(user.isEmpty()) {
//           throw new IllegalArgumentException("User not found by the provided id.");
//       }
//       User updatedUser = user.get();
//       updatedUser.setEmail(email);
//
//       return userRepository.save(updatedUser);
//    }

    @Override
    public User updateUserData(Integer id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("User not found by the provided id.");
        }
        User updatedUser = user.get();
        updatedUser.setEmail(userDto.getEmail()!= null ? userDto.getEmail() : updatedUser.getEmail());
        updatedUser.setName(userDto.getUsername()!= null ? userDto.getUsername() : updatedUser.getEmail());
        updatedUser.setSurname(userDto.getSurname()!= null ? userDto.getSurname() : updatedUser.getSurname());
        updatedUser.setPassword(userDto.getPassword()!= null ? userDto.getPassword() : updatedUser.getPassword());
        //updatedUser.setUser_role_id(userDto.getUserRoleId()!= null ? userDto.getUserRoleId() : updatedUser.getUser_role_id());
        updatedUser.setPhone(userDto.getPhone()!= null ? userDto.getPhone() : updatedUser.getPhone());
        updatedUser.setAddress(userDto.getAddress()!= null ? userDto.getAddress() : updatedUser.getAddress());

        updatedUser.setUpdatedDate(Instant.now());
        return userRepository.save(updatedUser);
    }
    @Override
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public DetailedUserResponseDto getUserDetails(Integer userId) throws NotFoundException {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()) {
            throw new NotFoundException("User with id " + userId + " not found.");
        }
        var user = userOpt.get();

        List<Review> reviews = reviewRepository.findAllByReviewedId(userId);
        List<Post> posts = postRepository.findAllByUserIdAndStatus(userId, Status.ACTIVE);

        DetailedUserResponseDto detailedUserResponseDto = new DetailedUserResponseDto();
        var detailedUser = new DetailedUserDto();

        detailedUser.setEmail(user.getEmail());
        detailedUser.setUsername(user.getUsername());
        detailedUser.setName(user.getName());
        detailedUser.setSurname(user.getSurname());
        detailedUser.setPhone(user.getPhone());
        detailedUser.setLocation(user.getLocation());
        detailedUser.setCity(user.getCity());
        detailedUser.setZip(user.getZip());

        List<CreateReviewDto> reviewDtos = reviews.stream()
                .map(this::mapToCreateReviewDto)
                .collect(Collectors.toList());

        List<PostDto> postDtos = posts.stream()
                .map(this::mapToPostDto)
                .collect(Collectors.toList());

        detailedUserResponseDto.setReviews(reviewDtos);
        detailedUserResponseDto.setPosts(postDtos);

        return detailedUserResponseDto;
    }

    private CreateReviewDto mapToCreateReviewDto(Review review) {
        return CreateReviewDto.builder()
                .reviewedUserId(review.getReviewedId())
                .reviewerId(review.getReviewerId())
                .rating(review.getRating())
                .createdAt(review.getDate())
                .build();
    }

    private PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .status(post.getStatus())
                .location(post.getLocation())
                .categoryId(post.getCategoryId())
                .userId(post.getUserId())
                .phoneNumber(post.getPhoneNumber())
                .createdDate(post.getCreatedDate())
                .build();
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (!user.isPresent()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        User foundUser = user.get();
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(foundUser.getUsername())
//                .password(foundUser.getPassword())
//                .roles(foundUser.getRole())
//                .build();
//    }

}
