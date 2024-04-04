package com.cross.recipe.controller;

import com.cross.recipe.entity.Recipe;
import com.cross.recipe.repository.RecipeRepository;
import com.cross.recipe.service.RecipeService;
import com.cross.recipe.service.RecipeServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Recipe>> getUserRecipes(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(recipeService.getRecipesByUserId(id));

    }

    @PostMapping("/newRecipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recipeService.createRecipe(recipe));
    }













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

}
