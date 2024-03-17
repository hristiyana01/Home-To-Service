package Backend.hometoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_post_photos")
public class UserPostPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "post_id")
    private Integer postId;
    @Column(name = "content")
    private String content;
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    @Lob
   // @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;
    @Column(name = "date")
    private Instant date = Instant.now();
}
