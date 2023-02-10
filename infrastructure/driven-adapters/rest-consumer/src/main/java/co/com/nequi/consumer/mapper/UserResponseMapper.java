package co.com.nequi.consumer.mapper;

import co.com.nequi.consumer.response.UserResponse;
import co.com.nequi.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResponseMapper {

    UserResponseMapper MAPPER = Mappers.getMapper(UserResponseMapper.class);
    @Mapping(source="id", target="userResponse.data.id")
    @Mapping(source="name", target="userResponse.data.first_name")
    @Mapping(source="lastName", target="userResponse.data.last_name")
    @Mapping(source="email", target="userResponse.data.email")

    User toDomain(UserResponse userResponse);
}
