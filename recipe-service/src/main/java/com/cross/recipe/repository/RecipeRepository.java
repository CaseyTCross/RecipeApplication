package com.cross.recipe.repository;

import com.cross.recipe.entity.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {

    public Recipe getRecipeById(long id);
}
