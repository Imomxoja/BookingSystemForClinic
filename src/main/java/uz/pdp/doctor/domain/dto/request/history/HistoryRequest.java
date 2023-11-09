package uz.pdp.doctor.domain.dto.request.history;

import lombok.*;
import uz.pdp.doctor.domain.entity.doctor.Speciality;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryRequest {

    private Speciality speciality;

    private UUID userId;

    private UUID doctorId;

    private LocalDateTime beginningTime;

    private LocalDateTime endingTime;
}
