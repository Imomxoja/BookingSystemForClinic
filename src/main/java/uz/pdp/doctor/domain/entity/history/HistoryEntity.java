package uz.pdp.doctor.domain.entity.history;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import uz.pdp.doctor.domain.entity.BaseEntity;
import uz.pdp.doctor.domain.entity.doctor.DoctorEntity;
import uz.pdp.doctor.domain.entity.doctor.Speciality;
import uz.pdp.doctor.domain.entity.user.UserEntity;

import java.time.LocalDateTime;

@Entity(name = "histories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryEntity extends BaseEntity {

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String doctorName;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime beginningTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endingTime;
}
