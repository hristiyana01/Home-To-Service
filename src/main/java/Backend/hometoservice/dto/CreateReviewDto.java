package Backend.hometoservice.dto;

import Backend.hometoservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDto {
    private Integer reviewedUserId;
    private Integer reviewerId;
    private Double rating;
    private Instant createdAt = Instant.now();
    private String location;
}
