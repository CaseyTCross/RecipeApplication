package com.cross.user.event;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRecipeEvent {

    long userId;
    long recipeId;

    @ElementCollection
    List<Long> userIdList;
    @ElementCollection
    List<Long> recipeIdList;


}
