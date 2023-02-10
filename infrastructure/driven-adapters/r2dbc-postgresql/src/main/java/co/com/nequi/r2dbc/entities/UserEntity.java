package co.com.nequi.r2dbc.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.NonNullFields;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("user")
public class UserEntity {

    @Id
    private Long id;

    @Column("name")
    @NotBlank
    @Size(max=255)
    private String name;

    @Column("lastname")
    @Size(max=255)
    @NotBlank
    private String lastName;

    @Column("email")
    @Size(max = 255)
    @Email
    @NotBlank
    private String email;

    @CreatedDate
    @Column("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("modified_at")
    private LocalDateTime modifiedAt;
}
