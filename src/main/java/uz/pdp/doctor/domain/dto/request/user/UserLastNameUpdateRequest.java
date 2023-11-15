package uz.pdp.doctor.domain.dto.request.user;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLastNameUpdateRequest {
    private UUID id;

    @Pattern(regexp = "[A-Za-z]+", message = "Lastname should not be empty!")
    private String lastName;
}
