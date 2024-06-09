package app.dtos.post;

import app.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostDto {
    private String title;
    private String description;
    private Double price;
    private String status;
    private String location;
    private String categoryId;
}