package uz.pdp.doctor.domain.dto.request.doctor;

import lombok.*;
import uz.pdp.doctor.domain.entity.doctor.Speciality;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorRequest {

    private String name;

    private String lastName;

    private String password;

    private String email;

    private Speciality speciality;
}
