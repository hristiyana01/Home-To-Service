package Backend.hometoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "reviewed_user_id")
    private Integer reviewedId;
    @Column(name = "reviewer_id")
    private Integer reviewerId;
    @Column(name = "date")
    private Instant date = Instant.now();
//    @Column(name = "time")
//    private String time;
   // @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Integer userId; // This is the email of the user who created the review
    private String provider; // This is the email of the provider who received the review
}
