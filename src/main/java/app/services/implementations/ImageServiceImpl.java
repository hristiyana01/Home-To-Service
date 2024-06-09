package app.services.implementations;

import app.models.Image;
import app.models.Post;
import app.repositories.ImageRepository;
import app.repositories.PostRepository;
import app.services.ImageService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final PostRepository postRepository;

    @Override
    public List<String> getAllForPost(Integer postId){
        List<Image> images = imageRepository.findAllByPostId(postId);
        return images.stream()
                .map(Image::getFilePath)
                .collect(Collectors.toList());
    }

    @Override
    public String uploadImage(MultipartFile[] imageFiles, Integer postId) throws NotFoundException, IOException {
        Optional<Post> optionalPost = postRepository.findById(postId);

        if (optionalPost.isEmpty()) {
            throw new NotFoundException("Post with id " + postId + " not found.");
        }

        Post post = optionalPost.get();

        for (int i = 0; i < imageFiles.length; i++) {
            String originalFilename = imageFiles[i].getOriginalFilename();
            String directory = "/Users/hristiyanashopova/Documents/Diploma/Home-To-Service/fe/home-to-service/public";
            File dest = new File(directory + "/" + originalFilename);
            imageFiles[i].transferTo(dest);

            Image image = new Image();
            image.setPost(post);
            image.setFilePath(dest.getAbsolutePath());
            image.setOriginalFilename(originalFilename);
            imageRepository.save(image);
        }

        return "Image uploaded successfully";
    }
}
