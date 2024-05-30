package Backend.hometoservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

public interface ImageService {
    String uploadImage(MultipartFile[] imageFiles, Integer postid) throws IOException;
    byte[] downloadImage(String imageName) throws DataFormatException, IOException;

}
