package com.dyer.frameworks.repository;

import com.dyer.frameworks.domain.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository <code>interface</code> accesses data
 * related to {@link Review} objects.
 */
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
}
