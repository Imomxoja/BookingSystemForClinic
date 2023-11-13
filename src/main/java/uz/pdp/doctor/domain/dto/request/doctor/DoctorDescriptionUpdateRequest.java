package uz.pdp.doctor.domain.dto.request.doctor;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDescriptionUpdateRequest {
    private UUID id;

    @Pattern(regexp = "\\w+", message = "Description should not be empty!")
    private String description;
}
