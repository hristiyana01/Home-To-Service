package app.services;

import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<String> getAllForPost(Integer postId);

    String uploadImage(MultipartFile[] imageFiles, Integer postId) throws NotFoundException, IOException;
}
