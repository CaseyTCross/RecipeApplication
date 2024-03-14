package com.cross.recipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "recipe")
@Getter
@Setter
@RequiredArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String recipeName;
    String recipeCreatorUserName;
    int recipeRating;

    @ElementCollection
    @CollectionTable(name = "ingredient_list", joinColumns=@JoinColumn(name = "recipe_id"))
    @Column(name = "ingredients")
    @OrderColumn(name = "ingredient_order")
    List<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "instructions_list", joinColumns=@JoinColumn(name = "recipe_id"))
    @Column(name = "instructions")
    @OrderColumn(name = "instruction_order")
    List<String> instructions;


}
