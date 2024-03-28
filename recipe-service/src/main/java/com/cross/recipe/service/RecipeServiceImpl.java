package com.cross.recipe.service;

import com.cross.recipe.entity.Recipe;
import com.cross.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public Recipe listUserRecipes(Long id) {
        recipeRepository.findAllById(id);
    }
}
