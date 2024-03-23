package Backend.hometoservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "user_role_id")
    private Integer user_role_id;
    @Column(name = "location_id")
    private Integer location_id;
    @Column(name = "phone_number")
    private String phone;
    @Column (name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
   // private String profileImage;
   // private String aboutMe;
//    @Column(name = "description")
//    private String description;
    @Column(name = "state")
    private String state;
    @Column(name = "zip")
    private String zip;
    @Column(name = "updated_date")
    private Instant updatedDate;
    @Column(name = "registeredDate")
    private Instant registeredDate;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Favourites> favourites = new HashSet<>();
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Post> posts;
}
