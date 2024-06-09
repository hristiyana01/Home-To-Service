package app.models;

import app.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "description")
    @NonNull
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "location")
    @NonNull
    private String location;

    @Column(name = "status")
    private Status status;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "created_date")
    @NonNull
    private Instant createdDate;

    @Column(name = "updated_date")
    @NonNull
    private Instant updatedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
