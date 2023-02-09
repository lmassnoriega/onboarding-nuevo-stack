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

    public Mono<User> saveDBUser(User userToSave) {
        return userDBRepository.saveUser(userToSave);
    }

    public Mono<User> saveExternalUser(Long id) {
        return userDBRepository.getById(id).flatMap(user -> Mono.error(
                new BusinessException("User already exists"))).switchIfEmpty(
                Mono.defer(() -> userExternalRepository.getUserById(id)
                        .flatMap(userDBRepository::saveUser))).cast(User.class);
    }

    public Mono<User> getUserById(Long id) {
        return userDBRepository.getById(id)
                .switchIfEmpty(Mono.error(new BusinessException("User not found")));
    }

    public Flux<User> getAllUsers() {
        return userDBRepository.getAllUsers();
    }

    public Flux<User> getAllByName(String nameToLookup) {
        return userDBRepository.getByName(nameToLookup);
    }
}
