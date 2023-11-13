package uz.pdp.doctor.domain.dto.request.doctor;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorNameUpdateRequest {

    private UUID id;

    @Pattern(regexp = "[A-Za-z]+", message = "Name should not be empty!")
    private String name;

}
