package uz.pdp.doctor.domain.dto.request.doctor;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorLastNameUpdateRequest {

    private UUID id;

    private String lastName;
}
