package Backend.hometoservice.dto;


import Backend.hometoservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostDto {
    private Status status;
    private String title;
    private String content;
    private Double price;
}