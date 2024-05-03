package Backend.hometoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostDto {
    private String status;
    private String title;
    private String content;
    private Double price;
}
