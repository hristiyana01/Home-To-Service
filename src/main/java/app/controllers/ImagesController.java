package app.controllers;

import app.services.ImageService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@AllArgsConstructor
public class ImagesController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("images") MultipartFile[] files, @RequestParam("postId") Integer postId) {
        try {
            String response = imageService.uploadImage(files, postId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IOException | NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());//Failed to upload image");
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getForPost(@PathVariable("postId") Integer postId)  {
            var images = imageService.getAllForPost(postId);
            return ResponseEntity.status(HttpStatus.OK).body(images);
    }
}
