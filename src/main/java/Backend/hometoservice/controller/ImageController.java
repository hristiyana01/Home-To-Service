package Backend.hometoservice.controller;

import Backend.hometoservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(@RequestParam("images") MultipartFile[] files,
                                         @RequestParam("postId") Integer postId) throws IOException {
        String uploadImage = imageService.uploadImage(files, postId);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @PostMapping("/upload-one")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,
                                         @RequestParam("postId") Integer postId) throws IOException {
        var files = new MultipartFile[1];
        files[0] = file;
        String uploadImage = imageService.uploadImage(files, postId);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws DataFormatException, IOException {
        byte[] imageData=imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

//ne mi trqbva da se svalqt snimki- daje da e zabraneno! (only screenshots)
//    @GetMapping("/{fileName}")
//    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
//        byte[] imageData = imageService.downloadImage(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
//                .body(imageData);
//    }
}