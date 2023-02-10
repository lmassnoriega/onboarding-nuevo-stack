package co.com.nequi.r2dbc.repository;

import co.com.nequi.r2dbc.entities.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long>{

    static String findAllByNameQuery = "SELECT * FROM user us WHERE lower(us.name) LIKE LOWER(CONCAT('%', :name, '%'))";
    @Query(findAllByNameQuery)
    Flux<UserEntity> findAllByName(String name);
}
