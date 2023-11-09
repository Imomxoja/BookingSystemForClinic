package uz.pdp.doctor.domain.dto.request.user;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserNameUpdateRequest {

    private UUID id;

    private String name;

}
