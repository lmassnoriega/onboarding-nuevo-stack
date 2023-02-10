package co.com.nequi.usecase.user;

import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.user.User;
import co.com.nequi.model.user.gateways.UserExternalRepository;
import co.com.nequi.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userDBRepository;
    private final UserExternalRepository userExternalRepository;

    /**
     * Saves a user to local storage
     *
     * @param userToSave
     *         User request body information to be persisted.
     * @return A {@link Mono} of the user just persisted to local storage
     */
    public Mono<User> saveDBUser(User userToSave) {
        return userDBRepository.saveUser(userToSave);
    }

    /**
     * Obtains a user from external source using the provided identifier and
     * then saves it to local storage.
     *
     * @param id
     *         Identifier belonging to the user being retrieved.
     * @return {@link User} User from external storage
     */
    public Mono<User> saveExternalUser(Long id) {
        return userDBRepository.getById(id).flatMap(user -> Mono.error(
                new BusinessException("User already exists"))).switchIfEmpty(
                Mono.defer(() -> userExternalRepository.getUserById(id)
                        .flatMap(userDBRepository::saveUser))).cast(User.class);
    }

    /**
     * Obtains a user from local storage using the provided identifier.
     *
     * @param id
     *         Identifier belonging to the user being retrieved.
     * @return {@link Mono} User from local storage
     */
    public Mono<User> getUserById(Long id) {
        return userDBRepository.getById(id).switchIfEmpty(
                Mono.error(new BusinessException("User not found")));
    }

    /**
     * Returns all available {@link User} from local storage
     *
     * @return A {@link Flux} of users from local storage.
     */
    public Flux<User> getAllUsers() {
        return userDBRepository.getAllUsers();
    }

    /**
     * Queries all users given the value of name
     *
     * @param nameToLookup
     *         param name to look for user in the database.
     * @return A {@link Flux} of users from local storage.
     */
    public Flux<User> getAllByName(String nameToLookup) {
        return userDBRepository.getByName(nameToLookup);
    }
}
