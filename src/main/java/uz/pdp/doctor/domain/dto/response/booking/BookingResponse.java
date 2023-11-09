package uz.pdp.doctor.domain.dto.response.booking;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import uz.pdp.doctor.domain.entity.doctor.DoctorEntity;
import uz.pdp.doctor.domain.entity.user.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    protected UUID id;

    private UserEntity user;

    private DoctorEntity doctor;

    private LocalDateTime beginningTime;

    private LocalDateTime endingTime;

    protected LocalDateTime createdDate;

    protected LocalDateTime updatedDate;

}
