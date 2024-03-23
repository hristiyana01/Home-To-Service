package Backend.hometoservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "location")
    private String location;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    private Instant createdDate;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "phone_number")
    private String phoneNumber;

    //    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "category_id", nullable = false, insertable=false, updatable=false)
    private Category category;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name = "user_id", nullable = false, insertable=false, updatable=false)
    private User user;
    @OneToMany(mappedBy = "post")
    @JsonBackReference
    private List<Favourites> favourites;
}
