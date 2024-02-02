package com.example.hometoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Favourites {
    @Id
    private Integer id;
    private Integer appUserId;
    private Integer postId;
    private String favouriteDate;
}
