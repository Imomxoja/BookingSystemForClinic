package uz.pdp.doctor.domain.dto.response.doctor;

import lombok.*;
import uz.pdp.doctor.domain.entity.doctor.Speciality;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponse {

    protected UUID id;

    private String name;

    private String lastName;

    private String email;

    private String description;

    private Speciality speciality;

    private LocalDateTime beginningWorkTime;

    private LocalDateTime endingWorkTime;

    protected LocalDateTime createdDate;

    protected LocalDateTime updatedDate;
}
