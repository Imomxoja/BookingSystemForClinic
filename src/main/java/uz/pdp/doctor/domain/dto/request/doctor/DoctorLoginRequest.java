package uz.pdp.doctor.domain.dto.request.doctor;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorLoginRequest {

    private String email;

    private String password;
}
