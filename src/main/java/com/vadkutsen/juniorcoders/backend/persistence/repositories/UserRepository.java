package com.vadkutsen.juniorcoders.backend.persistence.repositories;

import com.vadkutsen.juniorcoders.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    /**
     * Returns User given a username of null if not found.
     * @param username Theusername
     * @return User given a username of null if not found.
     */
    public User findByUsername(String username);

}
