package Backend.hometoservice.dto;

import Backend.hometoservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Integer id;
    private String title;
    private String description;
    private Status status;
    private String location;
    private Integer categoryId;
    private Integer userId;
    private String phoneNumber;
    private Instant createdDate;
    // private List<CreateUserPostPhotoDto> userPostPhotos;
}