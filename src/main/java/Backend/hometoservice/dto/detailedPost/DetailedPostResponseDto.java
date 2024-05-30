package Backend.hometoservice.dto.detailedPost;

import Backend.hometoservice.dto.CreateCommentDto;
import Backend.hometoservice.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedPostResponseDto {
    DetailedPostDto post;
    List<CreateCommentDto> comments;
    SellerDto user;
    List<Image> images;
}
