package uz.pdp.doctor.domain.dto.response.user;

import lombok.*;
import uz.pdp.doctor.domain.entity.user.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    protected UUID id;

    private String name;

    private String lastName;

    private String password;

    private String email;

    private UserRole role;

    protected LocalDateTime createdDate;

    protected LocalDateTime updatedDate;

}
