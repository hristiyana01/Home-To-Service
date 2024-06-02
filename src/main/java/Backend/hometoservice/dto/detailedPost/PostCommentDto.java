package Backend.hometoservice.dto.detailedPost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDto {
    private Integer id;
    private String text;
    private Integer userId;
    private String username;
    private Instant commentDate;

}
