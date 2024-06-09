package app.dtos.user;

import app.dtos.CreateReviewDto;
import app.models.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedUserResponseDto {
    List<Post> posts;
    List<CreateReviewDto> reviews;
}
