package Backend.hometoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "favourites")
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "appUserId")
    private Integer appUserId;
    @Builder.Default
    @Column(name = "favouriteDate")
    private Instant favoriteDate = Instant.now();
    @Column(name = "post_id")
    private Integer postId;
}
