package uz.pdp.doctor.controller.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.doctor.domain.dto.request.doctor.DoctorRequest;
import uz.pdp.doctor.domain.dto.response.doctor.DoctorResponse;
import uz.pdp.doctor.domain.entity.doctor.DoctorEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorConverter {

    private final ModelMapper modelMapper;
//    private final PasswordEncoder passwordEncoder;

    public DoctorEntity toDoctorEntity(DoctorRequest doctorRequest){
        DoctorEntity doctor = modelMapper.map(doctorRequest, DoctorEntity.class);
//        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return doctor;
    }

    public DoctorEntity toDoctorEntity(DoctorResponse doctorResponse){
        return modelMapper.map(doctorResponse, DoctorEntity.class);
    }

    public DoctorResponse toDoctorResponse(DoctorEntity doctor){
        return modelMapper.map(doctor, DoctorResponse.class);
    }

    public List<DoctorResponse> toDoctorResponse(List<DoctorEntity> doctors){
        return modelMapper.map(doctors,
                new TypeToken<List<DoctorResponse>>(){}.getType());
    }
}
