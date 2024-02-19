package Backend.hometoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String email;
    private String password;
    private String role;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String profileImage;
    private String description;
    private String serviceType;

}
