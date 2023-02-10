package co.com.nequi.r2dbc;

import co.com.nequi.model.user.User;
import co.com.nequi.r2dbc.adapter.UserRepositoryAdapter;
import co.com.nequi.r2dbc.entities.UserEntity;
import co.com.nequi.r2dbc.mapper.UserEntityMapper;
import co.com.nequi.r2dbc.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyReactiveRepositoryAdapterTest {

    @InjectMocks
    UserRepositoryAdapter repositoryAdapter;

    @Mock
    UserRepository repository;

    @Test
    void mustFindValueById() {

        UserEntity userEntity = UserEntity.builder().id(1L).name("Jameson")
                .lastName("Locke").email("aaaa@example.com").build();
        User domainUser = User.builder().id(1L).name("Jameson")
                .lastName("Locke").email("aaaa@example.com").build();
        when(repository.findById(1L)).thenReturn(Mono.just(userEntity));

        Mono<User> result = repositoryAdapter.getById(1L);

        StepVerifier.create(result)
                .expectNextMatches(value -> value.equals(domainUser))
                .verifyComplete();
    }
}
