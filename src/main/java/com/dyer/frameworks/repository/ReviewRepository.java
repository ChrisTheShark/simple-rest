package com.dyer.frameworks.repository;

import com.dyer.frameworks.domain.Review;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Repository <code>interface</code> accesses data
 * related to {@link Review} objects.
 */
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reviewRepository.findOne(#id)?.user?.username == authentication.name")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #review.user?.username == authentication.name")
    void delete(@Param("review") Review review);

}
