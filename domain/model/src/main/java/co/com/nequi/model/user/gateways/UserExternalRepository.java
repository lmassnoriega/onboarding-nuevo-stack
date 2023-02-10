package co.com.nequi.model.user.gateways;

import co.com.nequi.model.user.User;
import reactor.core.publisher.Mono;

public interface UserExternalRepository {

    /**
     * Obtains a user from external source using the provided identifier.
     *
     * @param id
     *         Identifier belonging to the user being retrieved.
     * @return {@link User} User from external storage
     */
    Mono<User> getUserById(Long id);
}
