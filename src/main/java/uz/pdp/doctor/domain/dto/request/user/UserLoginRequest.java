package uz.pdp.doctor.domain.dto.request.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequest {

    private String email;

    private String password;
}
