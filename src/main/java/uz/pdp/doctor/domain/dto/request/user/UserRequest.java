package uz.pdp.doctor.domain.dto.request.user;


import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @Pattern(regexp = "[A-Za-z]+", message = "Name should not be empty!")
    private String name;

    @Pattern(regexp = "[A-Za-z]+", message = "Lastname should not be empty!")
    private String lastName;

    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email doesn't much")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must consist of at least 8 numbers and letters and must not contain symbols")
    private String password;
}
