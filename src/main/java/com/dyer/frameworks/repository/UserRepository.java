package com.dyer.frameworks.repository;

import com.dyer.frameworks.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository <code>interface</code> accesses data
 * related to {@link User} objects.
 */
@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find a {@link User} object by a provided {@link String} username.
     * @param username the provided {@link String} username
     * @return a located {@link User} object
     */
    User findByUsername(String username);

}
