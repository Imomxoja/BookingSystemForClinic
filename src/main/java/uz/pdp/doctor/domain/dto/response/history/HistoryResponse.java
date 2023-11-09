package uz.pdp.doctor.domain.dto.response.history;

import lombok.*;
import uz.pdp.doctor.domain.entity.doctor.DoctorEntity;
import uz.pdp.doctor.domain.entity.doctor.Speciality;
import uz.pdp.doctor.domain.entity.user.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryResponse {

    protected UUID id;

    private String userName;

    private String doctorName;

    private Speciality speciality;

    private UserEntity user;

    private DoctorEntity doctor;

    private LocalDateTime beginningTime;

    private LocalDateTime endingTime;

    protected LocalDateTime createdDate;

    protected LocalDateTime updatedDate;

}
