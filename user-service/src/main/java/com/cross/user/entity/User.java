package com.cross.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection
    private List<Long> createdRecipeList;

    @ElementCollection
    private List<Long> addedRecipeList;

    @ElementCollection
    private List<Long> favoriteRecipeList;

    private String email;
    private String firstName;
    private String lastName;
}
