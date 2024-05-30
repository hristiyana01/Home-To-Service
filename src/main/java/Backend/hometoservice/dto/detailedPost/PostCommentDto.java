package Backend.hometoservice.dto.detailedPost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDto {
    private Integer postId;
    private String commentText;
    private Integer userId;
    // private Instant createdAt = Instant.now();
}
