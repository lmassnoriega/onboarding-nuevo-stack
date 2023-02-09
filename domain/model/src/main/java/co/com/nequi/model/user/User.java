package co.com.nequi.model.user;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class User {

    private Long id;
    private String name;
    private String lastName;
    private String email;
}
