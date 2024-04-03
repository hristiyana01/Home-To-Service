package Backend.hometoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateComment {
    private Integer postId;
    private String commentText;
    private Integer userId;
   // private Instant createdAt = Instant.now();
}
