package com.cross.user.service;

import com.cross.user.entity.User;
import com.cross.user.event.UserRecipeEvent;
import com.cross.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserRecipeEvent> kafkaTemplate;




    @EventListener
    public void sendRecipeIdsToRecipeService(UserRecipeEvent userRecipeEvent) {
        List<Long> allRecipeIds = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(userRecipeEvent.getUserId());
        User user = optionalUser.orElse(null);
        if(user != null) {
            allRecipeIds.addAll(user.getAddedRecipeList());
            allRecipeIds.addAll(user.getFavoriteRecipeList());
            allRecipeIds.addAll(user.getCreatedRecipeList());
        }
        userRecipeEvent.setRecipeIdList(allRecipeIds);
        kafkaTemplate.send("receive-recipe-id-topic", userRecipeEvent);

    }

}
