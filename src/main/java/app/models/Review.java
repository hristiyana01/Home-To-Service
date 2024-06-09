package app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "reviewedUserId")
    private Integer reviewedUserId;

    @Column(name = "reviewerId")
    private Integer reviewerId;

    @Column(name = "date")
    private Instant date;
}
