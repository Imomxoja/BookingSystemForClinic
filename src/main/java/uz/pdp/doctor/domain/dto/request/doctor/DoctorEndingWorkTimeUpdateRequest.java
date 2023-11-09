package uz.pdp.doctor.domain.dto.request.doctor;

import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorEndingWorkTimeUpdateRequest {
    private UUID id;

    private LocalTime endingWorkTime;
}
