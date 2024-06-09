package app.dtos.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostDto {
    private String title;
    private String description;
    private Double price;
    private String location;
    private String phoneNumber;
    private Integer categoryId;
    private Integer userId;
}
