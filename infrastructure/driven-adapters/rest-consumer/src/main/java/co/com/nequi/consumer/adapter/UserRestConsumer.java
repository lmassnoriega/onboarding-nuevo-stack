package co.com.nequi.consumer.adapter;

import co.com.nequi.consumer.mapper.UserResponseMapper;
import co.com.nequi.consumer.response.UserResponse;
import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.exceptions.TechnicalException;
import co.com.nequi.model.user.User;
import co.com.nequi.model.user.gateways.UserExternalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class UserRestConsumer implements UserExternalRepository {

    private final WebClient client;

    @Override
    public Mono<User> getUserById(Long id) {
        return client.get().uri("/" + id).retrieve()
                .onStatus(httpStatus -> httpStatus == HttpStatus.NOT_FOUND,
                        clientResponse -> clientResponse.bodyToMono(
                                String.class).flatMap(s -> Mono.error(
                                new BusinessException("External User not found",
                                        clientResponse.statusCode().value()))))
                .onStatus(HttpStatus::is5xxServerError,
                        clientResponse -> clientResponse.bodyToMono(
                                String.class).flatMap(s -> Mono.error(
                                new TechnicalException(s,
                                        clientResponse.statusCode().value()))))
                .bodyToMono(UserResponse.class).log()
                .map(UserResponseMapper.MAPPER::toDomain);
    }
}