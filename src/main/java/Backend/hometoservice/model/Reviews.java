package Backend.hometoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reviews {
    @Id
    private Integer id;
    private Integer rating;
    private String comment;
    private String date;
    private String time;
    private String appUser; // This is the email of the user who created the review
    private String provider; // This is the email of the provider who received the review
}
