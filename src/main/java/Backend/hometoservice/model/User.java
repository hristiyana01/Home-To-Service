package Backend.hometoservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(name = "surname")
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Column(name = "phone_number")
    private String phone;
    @Column (name = "address")
    @NotBlank(message = "Address is mandatory")
    private String address;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "zip")
    private String zip;
    @Column(name = "user_role_id")
    private Integer user_role_id;
    @Column(name = "location")
    private String location;
    @Column(name = "registeredDate")
    private Instant registeredDate;
    @Column(name = "updated_date")
    private Instant updatedDate;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Favourites> favourites = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Post> posts;
}
