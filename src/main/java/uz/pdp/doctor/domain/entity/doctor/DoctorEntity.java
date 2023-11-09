package uz.pdp.doctor.domain.entity.doctor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import uz.pdp.doctor.domain.entity.BaseEntity;
import uz.pdp.doctor.domain.entity.booking.BookingEntity;
import uz.pdp.doctor.domain.entity.history.HistoryEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DoctorEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String description;

    private Speciality speciality;

    @OneToMany(mappedBy = "doctor")
    private List<BookingEntity> bookings;

    @OneToMany(mappedBy = "doctor")
    private List<HistoryEntity> histories;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalTime beginningWorkTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalTime endingWorkTime;
}
