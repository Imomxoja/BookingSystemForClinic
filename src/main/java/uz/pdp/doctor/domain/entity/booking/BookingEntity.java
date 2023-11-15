package uz.pdp.doctor.domain.entity.booking;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import uz.pdp.doctor.domain.entity.BaseEntity;
import uz.pdp.doctor.domain.entity.doctor.DoctorEntity;
import uz.pdp.doctor.domain.entity.user.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bookings")
@Builder
public class BookingEntity extends BaseEntity {

   @ManyToOne()
   @JoinColumn(name = "user_id")
   private UserEntity user;

   @ManyToOne()
   @JoinColumn(name = "doctor_id")
   private DoctorEntity doctor;

   @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
   private LocalDateTime beginningTime;

   @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
   private LocalDateTime endingTime;
}
