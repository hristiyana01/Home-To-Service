package Backend.hometoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String content;
    private String image;
    private String date;
    private String time;
    private String location;
    private Integer categoryId;
    private String status;
    private String appUser; // This is the email of the user who created the post
    private String provider; // This is the email of the provider who accepted the post

}
