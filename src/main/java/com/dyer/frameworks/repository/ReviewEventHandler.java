package com.dyer.frameworks.repository;

import com.dyer.frameworks.domain.Review;
import com.dyer.frameworks.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Streamlining {@link Review} creation by capturing the logged in
 * {@link User} to persist as the reviewer.
 */
@Component
@RepositoryEventHandler(Review.class)
public class ReviewEventHandler {

    @Autowired
    private UserRepository userRepository;

    @HandleBeforeCreate
    public void addReviewerBasedOnLoggedInUser(Review review) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        review.setUser(user);
    }

}
