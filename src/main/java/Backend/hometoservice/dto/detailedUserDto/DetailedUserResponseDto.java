package Backend.hometoservice.dto.detailedUserDto;

import Backend.hometoservice.dto.CreateCommentDto;
import Backend.hometoservice.dto.CreateReviewDto;
import Backend.hometoservice.dto.PostDto;
import Backend.hometoservice.model.Review;
import Backend.hometoservice.model.Post;
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
    List<PostDto> posts;
    List<CreateReviewDto> reviews;

}
