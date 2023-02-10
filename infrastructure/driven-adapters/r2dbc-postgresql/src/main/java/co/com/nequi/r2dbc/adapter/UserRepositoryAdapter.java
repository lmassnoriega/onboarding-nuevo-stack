package co.com.nequi.r2dbc.adapter;

import co.com.nequi.model.user.User;
import co.com.nequi.r2dbc.mapper.UserEntityMapper;
import co.com.nequi.r2dbc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class UserRepositoryAdapter
        implements co.com.nequi.model.user.gateways.UserRepository {
    private final UserRepository userRepository;

    @Override
    public Mono<User> getById(Long id) {
        return userRepository.findById(id)
                .map(UserEntityMapper.MAPPER::toDomain);
    }

    @Override
    public Mono<User> saveUser(User userToSave) {
        userToSave.setId(null);
        return userRepository.save(UserEntityMapper.MAPPER.toEntity(userToSave))
                .map(UserEntityMapper.MAPPER::toDomain);
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll().map(UserEntityMapper.MAPPER::toDomain);
    }

    @Override
    public Flux<User> getByName(String name) {
        return userRepository.findAllByName(name)
                .map(UserEntityMapper.MAPPER::toDomain);
    }
}
