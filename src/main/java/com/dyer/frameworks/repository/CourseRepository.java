package com.dyer.frameworks.repository;

import com.dyer.frameworks.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Repository <code>interface</code> accesses data
 * related to {@link Course} objects.
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    /**
     * Simple finder method to lookup {@link Course}s by a provided {@link String} title.
     * @param title the provided {@link String} title
     * @param page the {@link Pageable} page object
     * @return a located {@link Page} of {@link Course} objects.
     */
    @RestResource(rel = "title-contains", path = "containsTitle")
    Page<Course> findByTitleContaining(@Param("title") String title, Pageable page);

}
