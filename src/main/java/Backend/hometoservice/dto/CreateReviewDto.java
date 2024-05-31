package Backend.hometoservice.dto;

import Backend.hometoservice.model.User;
//import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDto{
    private Integer reviewedUserId;
    private Integer reviewerId;
    //TODO: the rating should be between 0 and 6
    //TODO: the same reviewer can make review to same reviewedUser only once
    //@Max(value = 6, message = "The value must be less than or equal to 6")
    private Double rating;
    private Instant createdAt = Instant.now();

}
