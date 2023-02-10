package co.com.nequi.consumer.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private DataType data;
    private SupportType support;

}


