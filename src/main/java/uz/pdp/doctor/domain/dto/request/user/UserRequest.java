package uz.pdp.doctor.domain.dto.request.user;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String name;

    private String lastName;

    private String password;

    private String email;

}
