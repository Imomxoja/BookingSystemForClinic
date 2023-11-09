package uz.pdp.doctor.domain.dto.request.doctor;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDescriptionUpdateRequest {
    private UUID id;

    private String description;
}
