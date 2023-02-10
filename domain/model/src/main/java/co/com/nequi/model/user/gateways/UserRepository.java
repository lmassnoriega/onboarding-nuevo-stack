package co.com.nequi.model.user.gateways;

import co.com.nequi.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    /**
     * Obtains a user from local storage using the provided identifier.
     *
     * @param id
     *         Identifier belonging to the user being retrieved.
     * @return {@link User} User from local storage
     */
    Mono<User> getById(Long id);

    /**
     * Saves a user to local storage
     *
     * @param userToSave
     *         User request body information to be persisted.
     * @return A {@link Mono} of the user just persisted to local storage
     */
    Mono<User> saveUser(User userToSave);

    /**
     * Returns all available {@link User} from local storage
     *
     * @return A {@link Flux} of users from local storage.
     */
    Flux<User> getAllUsers();

    /**
     * Queries all users given the value of name
     *
     * @param name
     *         param name to look for user in the database.
     * @return A {@link Flux} of users from local storage.
     */
    Flux<User> getByName(String name);
}
