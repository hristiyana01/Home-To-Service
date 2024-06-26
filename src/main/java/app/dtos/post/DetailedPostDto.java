package app.dtos.post;

import app.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedPostDto {

    private Integer id;
    private String title;
    private String description;
    private Status status;
    private String location;
    private Integer categoryId;
    private Integer userId;
    private String phoneNumber;
    private Instant createdDate;
    private Double price;

}
