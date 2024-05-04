package Backend.hometoservice.dto;

import Backend.hometoservice.enums.Status;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostDto {
    private String title;
    private String description;
    private Status status;
    private String location;
    private Integer categoryId;
    private Integer userId;
    private String phoneNumber;
   // private List<CreateUserPostPhotoDto> userPostPhotos;
}
