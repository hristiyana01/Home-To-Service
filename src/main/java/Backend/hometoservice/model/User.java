package Backend.hometoservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
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

    public User(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
  //  @NotBlank(message = "Email is mandatory")
    private String email;
    @Column(name = "password")
//    @NotBlank(message = "Password is mandatory")
    private String password;
  //  @NotBlank(message = "Username is mandatory")
    @Column(name = "username")
    private String username;
    @Column(name = "name")
 //   @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(name = "surname")
 //   @NotBlank(message = "Surname is mandatory")
    private String surname;
    //@Column(name = "user_role_id")
    //private Integer user_role_id;
    @Column(name = "location")
    private String location;
    @Column(name = "phone_number")
    private String phone;
    @Column (name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "zip")
    private String zip;
    @Column(name = "updated_date")
    private Instant updatedDate;
    @Column(name = "registeredDate")
    private Instant registeredDate;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Favourites> favourites = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Post> posts;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Role> roles = new HashSet<>();
}
