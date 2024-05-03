package Backend.hometoservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "post_id")
    private Integer postId;
    @Column(name = "comment_text")
    @NotBlank(message = "Text could not be blank.")
    private String commentText;
    @CreationTimestamp
    @Column(name = "comment_date")
    private Instant createdAt = Instant.now();
    @UpdateTimestamp
    @Column(name = "updated_date")
    private Instant updatedDate;
    @Column(name = "user_id")
    private Integer userId;
}
