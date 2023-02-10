package co.com.nequi.consumer.response;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class DataType {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String avatar;
}
