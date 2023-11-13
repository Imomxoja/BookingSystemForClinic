package uz.pdp.doctor.domain.dto.request.booking;


import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {

    private UUID userId;

    private UUID doctorId;

    private LocalDateTime beginningTime;

    private LocalDateTime endingTime;

}
