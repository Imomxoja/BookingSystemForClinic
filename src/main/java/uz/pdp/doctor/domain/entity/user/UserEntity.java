package uz.pdp.doctor.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.doctor.domain.entity.BaseEntity;
import uz.pdp.doctor.domain.entity.booking.BookingEntity;
import uz.pdp.doctor.domain.entity.history.HistoryEntity;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

     @Column(nullable = false)
     private String name;

     @Column(nullable = false)
     private String lastName;

     @Column(nullable = false)
     private String password;

     @Column(unique = true, nullable = false)
     private String email;

     @Enumerated(EnumType.STRING)
     private UserRole role;

     @OneToMany(mappedBy = "user")
     private List<BookingEntity> bookings;

     @OneToMany(mappedBy = "user")
     private List<HistoryEntity> histories;
}
