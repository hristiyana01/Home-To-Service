package Backend.hometoservice.dto.detailedPost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {
   // private String email;
    private Integer id;
    private String username;
    private String name;
    private String surname;
    //private Integer userRoleId;
    private String phone;
    private String country;

}
