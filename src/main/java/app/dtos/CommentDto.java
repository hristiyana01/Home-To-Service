package app.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "username")
    private String username;
}
