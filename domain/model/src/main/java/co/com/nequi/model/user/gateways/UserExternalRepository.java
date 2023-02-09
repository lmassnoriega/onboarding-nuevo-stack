package co.com.nequi.model.user.gateways;

import co.com.nequi.model.user.User;
import reactor.core.publisher.Mono;

public interface UserExternalRepository {
    
    Mono<User> getUserById(Long id);
}
