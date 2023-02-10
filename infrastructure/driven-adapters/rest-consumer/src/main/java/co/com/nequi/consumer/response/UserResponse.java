package co.com.nequi.consumer.response;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class UserResponse {
    private DataType data;
    private SupportType support;

}


