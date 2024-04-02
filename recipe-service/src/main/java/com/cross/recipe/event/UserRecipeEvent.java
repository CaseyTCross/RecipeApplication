package com.cross.recipe.event;

import jakarta.persistence.ElementCollection;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
public class UserRecipeEvent {

    long userId;
    long recipeId;

    @ElementCollection
    List<Long> userIdList;
    @ElementCollection
    List<Long> recipeIdList;

}
