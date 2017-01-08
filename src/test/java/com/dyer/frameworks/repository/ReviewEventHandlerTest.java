package com.dyer.frameworks.repository;

import com.dyer.frameworks.domain.Review;
import com.dyer.frameworks.domain.User;
import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

/**
 * Testing for {@link ReviewEventHandler}.
 */
public class ReviewEventHandlerTest {

    @Test
    public void testAddReviewerBasedOnLoggedInUser() throws Exception {
        final User user = new User("chewie", "Chewbacca", "Kashyyyk", "arrrrhhh", new String[0]);

        /*
         * Create a mock Authentication object to contain the authenticated User.
         */
        Authentication mockAuthentication = EasyMock.createMock(Authentication.class);
        EasyMock.expect(mockAuthentication.getName()).andReturn(user.getUsername());

        /*
         * Create a mock SecurityContext to hold the Authentication. Give this to the
         * holder for return during test.
         */
        SecurityContext mockContext = EasyMock.createMock(SecurityContext.class);
        EasyMock.expect(mockContext.getAuthentication()).andReturn(mockAuthentication);
        SecurityContextHolder.setContext(mockContext);

        /*
         * Create a mock UserRepository to return a mock user by username.
         */
        UserRepository mockRepository = EasyMock.createMock(UserRepository.class);
        EasyMock.expect(mockRepository.findByUsername(EasyMock.eq(
                user.getUsername()))).andReturn(user);

        EasyMock.replay(mockAuthentication, mockContext, mockRepository);

        /*
         * Hand wire dependencies to ReviewEventHandler.
         */
        ReviewEventHandler eventHandler = new ReviewEventHandler();
        ReflectionTestUtils.setField(eventHandler, "userRepository",
                mockRepository);

        Review review = new Review(null, null, 1, "ARRRH EHHHH");
        eventHandler.addReviewerBasedOnLoggedInUser(review);

        EasyMock.verify(mockAuthentication, mockContext, mockRepository);

        assertEquals("Expected user should be set on review.", review.getUser(), user);
    }

}
