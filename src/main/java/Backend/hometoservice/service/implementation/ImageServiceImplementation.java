package Backend.hometoservice.service.implementation;

//import Backend.hometoservice.ImageUtils;
import Backend.hometoservice.ImageUtils;
import Backend.hometoservice.model.Image;
import Backend.hometoservice.model.Post;
import Backend.hometoservice.repository.ImageRepository;
import Backend.hometoservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class ImageServiceImplementation implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public String uploadImage(MultipartFile[] imageFiles, Integer postId) throws IOException {

        List<Image> images = new ArrayList<Image>();
        for(MultipartFile image : imageFiles) {
            var imageToSave = Image.builder()
                    .postId(postId)
                    //.imageData(image.getBytes())
                    .name(image.getOriginalFilename())
                    .type(image.getContentType())
                    .imageData(ImageUtils.compressImage(image.getBytes()))
                    .build();

            images.add((imageToSave));
        }

        imageRepository.saveAll(images);

//        var imageToSave = Image.builder()
//                .imageData(imageFile.getBytes())
//                .post(Post.builder().build())
//                //.name(imageFile.getOriginalFilename())
//                //.type(imageFile.getContentType())
//                //.imageData(ImageUtils.compressImage(imageFile.getBytes()))
//                .build();

        //imageRepository.save(imageToSave);
        return "file uploaded successfully : ";
    }

    public byte[] downloadImage(String fileName) throws DataFormatException, IOException {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }


}
