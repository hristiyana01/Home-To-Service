package Backend.hometoservice.model;

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
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "location")
    private String location;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    private Instant createdDate;
    @Column(name = "creator")
    private Integer user_creator;
    @Column(name = "phone_number")
    private String phoneNumber;

}
