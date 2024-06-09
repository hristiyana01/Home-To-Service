package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "registered_date")
    private Instant registered_date;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Favourites> favourites = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();
}
