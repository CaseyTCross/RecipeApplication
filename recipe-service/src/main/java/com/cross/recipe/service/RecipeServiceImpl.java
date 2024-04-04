package com.cross.recipe.service;
import com.cross.recipe.configuration.WebClientConfig;
import com.cross.recipe.entity.Recipe;
import com.cross.recipe.event.UserRecipeEvent;
import com.cross.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{


    private final WebClient.Builder webClientBuilder;

    private final RecipeRepository recipeRepository;


    public List<Recipe> getRecipesByUserId(Long userId) {
        UserRecipeEvent userRecipeEvent = new UserRecipeEvent();
        userRecipeEvent.setUserId(userId);
        UserRecipeEvent returnedUserRecipeEvent;
        List<Long> userRecipeListIds = new ArrayList<>();
        try {
            returnedUserRecipeEvent =  webClientBuilder.build()
                    .post()
                    .uri("http://user-service:8080/internal/{FILL THIS IN ONCE BUILT}")
                    .bodyValue(userRecipeEvent)
                    .retrieve()
                    .bodyToMono(UserRecipeEvent.class)
                    .block(Duration.ofSeconds(10));
            userRecipeListIds = returnedUserRecipeEvent.getRecipeIdList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Recipe> userRecipesList = new ArrayList<>();
        if(userRecipeListIds != null && !userRecipeListIds.isEmpty()) {
            for(Long l : userRecipeListIds) {
                userRecipesList.add(recipeRepository.getRecipeById(l));
            }
        }
        return userRecipesList;

    }

    public Recipe createRecipe(Recipe recipe) {

    }




}