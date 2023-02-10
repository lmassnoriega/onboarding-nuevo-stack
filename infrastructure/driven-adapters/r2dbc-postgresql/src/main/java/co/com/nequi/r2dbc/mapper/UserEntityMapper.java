package co.com.nequi.r2dbc.mapper;

import co.com.nequi.model.user.User;
import co.com.nequi.r2dbc.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface UserEntityMapper {

    UserEntityMapper MAPPER = Mappers.getMapper(UserEntityMapper.class);

    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
}
