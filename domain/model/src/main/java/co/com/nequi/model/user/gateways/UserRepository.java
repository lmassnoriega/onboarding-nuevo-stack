package co.com.nequi.model.user.gateways;

import co.com.nequi.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> getById(Long id);
    Mono<User> saveUser(User userToSave);
    Flux<User> getAllUsers();
    Flux<User> getByName(String name);
}
