package dev.rdkln.tracker.user.domain;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserById(Long id);

}
