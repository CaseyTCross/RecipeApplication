package com.cross.recipe.controller;

import com.cross.recipe.entity.Recipe;
import com.cross.recipe.repository.RecipeRepository;
import com.cross.recipe.service.RecipeService;
import com.cross.recipe.service.RecipeServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeServiceImpl recipeService;

    @PostMapping("/seed")
    public void addSeedData() {
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setRecipeName("Tester");
        recipe.setRecipeCreatorUserName("Bob");
        List<String> instructions = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();
        instructions.add("Step 1");
        ingredients.add("EGG");
        recipe.setInstructions(instructions);
        recipe.setIngredients(ingredients);
        recipeRepository.save(recipe);

    }
    @GetMapping("/get")
    private Iterable<Recipe> get() {
        return recipeRepository.findAll();
    }
    @GetMapping("/kafka")
    private Iterable<Recipe> getter() {
        return recipeService.listUserRecipes((long)1);
    }

}
