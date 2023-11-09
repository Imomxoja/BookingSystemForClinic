package uz.pdp.doctor.domain.dto.request.doctor;

import lombok.*;
import uz.pdp.doctor.domain.entity.doctor.Speciality;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorSpecialityUpdateRequest {
    private UUID id;

    private Speciality speciality;
}
