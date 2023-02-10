package co.com.nequi.consumer.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataType {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String avatar;
}
