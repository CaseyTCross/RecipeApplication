package com.cross.recipe.service;
import com.cross.recipe.entity.Recipe;
import com.cross.recipe.event.UserRecipeEvent;
import com.cross.recipe.repository.RecipeRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final KafkaTemplate<String, UserRecipeEvent> kafkaTemplate;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, KafkaTemplate<String, UserRecipeEvent> kafkaTemplate) {
        this.recipeRepository = recipeRepository;
        this.kafkaTemplate = kafkaTemplate;
    }



    public List<Recipe> listUserRecipes(Long userId) {

        UserRecipeEvent userRecipeEvent = null;
        UserRecipeEvent sendUserRecipeEvent = new UserRecipeEvent();
        sendUserRecipeEvent.setUserId(userId);
        CompletableFuture<SendResult<String,UserRecipeEvent>> result = kafkaTemplate.send("send-user-id-topic", sendUserRecipeEvent);

        try {
            SendResult<String, UserRecipeEvent> sendResult = result.get();
            userRecipeEvent = sendResult.getProducerRecord().value();

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Recipe> recipeList = new ArrayList<>();
        if(userRecipeEvent != null && userRecipeEvent.getRecipeIdList() != null && recipeRepository != null) {
            for(long l : userRecipeEvent.getRecipeIdList()) {
                Recipe recipe = recipeRepository.getRecipeById(l);
                    if(recipe != null) {
                        recipeList.add(recipe);
                    }


            }
        }
        return recipeList;


    }




}