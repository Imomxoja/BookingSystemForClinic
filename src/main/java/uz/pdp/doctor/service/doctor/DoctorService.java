package uz.pdp.doctor.service.doctor;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.doctor.controller.converter.DoctorConverter;
import uz.pdp.doctor.domain.dto.request.doctor.*;
import uz.pdp.doctor.domain.dto.request.login.UserAndDoctorLoginRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.doctor.DoctorResponse;
import uz.pdp.doctor.domain.entity.doctor.DoctorEntity;
import uz.pdp.doctor.repository.doctor.DoctorRepository;
import uz.pdp.doctor.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class DoctorService implements BaseService<DoctorRequest, BaseResponse<DoctorResponse>> {

    private final DoctorRepository doctorRepository;
    private final DoctorConverter doctorConverter;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<DoctorResponse> create(DoctorRequest doctorRequest) {
        DoctorEntity doctorEntity = doctorConverter.toDoctorEntity(doctorRequest);
        doctorRepository.save(doctorEntity);

        return new BaseResponse<>("Success!", 200, doctorConverter.toDoctorResponse(doctorEntity),0);
    }

    @Override
    public BaseResponse<DoctorResponse> delete(UUID id) {
        BaseResponse<DoctorResponse> baseResponse = getById(id);

        if (baseResponse.getData() == null){
            return baseResponse;
        }

        doctorRepository.deleteById(id);

        return new BaseResponse<>("Success!", 200, null, 0);
    }

    @Override
    public BaseResponse<DoctorResponse> getById(UUID id) {
        Optional<DoctorEntity> optional = doctorRepository.findById(id);

        return optional.map(doctor -> new BaseResponse<>(
                        "Success!",
                        200, doctorConverter.toDoctorResponse(doctor), 0))
                .orElseGet(() -> new BaseResponse<>("User not found!", 404, null, 0));
    }

    public BaseResponse<DoctorResponse> findByEmail(String email){
        Optional<DoctorEntity> optional = doctorRepository.findDoctorEntitiesByEmail(email);

        return optional.map(doctor -> new BaseResponse<>(
                "Success!",
                200, doctorConverter.toDoctorResponse(doctor), 0))
                .orElseGet(() -> new BaseResponse<>("Doctor not found!", 404, null, 0));
    }

    public BaseResponse<DoctorResponse> login(UserAndDoctorLoginRequest doctorLoginRequest){
        BaseResponse<DoctorResponse> baseResponse = findByEmail(doctorLoginRequest.getEmail());

        if (baseResponse.getData() == null){
            return baseResponse;
        }

        DoctorEntity doctorEntity = doctorConverter.toDoctorEntity(baseResponse.getData());

//        if (passwordEncoder.matches(doctorLoginRequest.getPassword(), doctorEntity.getPassword())){
//            return new BaseResponse<>("Success!", 200, doctorConverter.toDoctorResponse(doctorEntity), 0);
//        }
        return new BaseResponse<>("Something went wrong!", 400, null, 0);
    }

    private BaseResponse<DoctorResponse> updateField(UUID id, Consumer<DoctorEntity> updateField) {
        BaseResponse<DoctorResponse> response = getById(id);

        if (response.getData() == null) {
            return response;
        }

        DoctorEntity doctorEntity = doctorConverter.toDoctorEntity(response.getData());
        updateField.accept(doctorEntity);

        DoctorEntity updatedDoctor = doctorRepository.save(doctorEntity);

        return new BaseResponse<>("Success!", 200, doctorConverter.toDoctorResponse(updatedDoctor), 0);
    }

    public BaseResponse<DoctorResponse> nameAndLastnameUpdate(DoctorNameAndLastnameUpdateRequest updateRequest) {
        BaseResponse<DoctorResponse> response = getById(updateRequest.getId());

        if (response.getData() == null) {
            return response;
        }

        DoctorEntity doctorEntity = doctorConverter.toDoctorEntity(response.getData());
        doctorEntity.setName(updateRequest.getName());
        doctorEntity.setLastName(updateRequest.getLastName());

        DoctorEntity updatedDoctor = doctorRepository.save(doctorEntity);

        return new BaseResponse<>("Success!", 200, doctorConverter.toDoctorResponse(updatedDoctor), 0);    }

    public BaseResponse<DoctorResponse> specialityUpdate(DoctorSpecialityUpdateRequest request) {
        return updateField(request.getId(), doctorEntity -> doctorEntity.setSpeciality(request.getSpeciality()));
    }

    public BaseResponse<DoctorResponse> descriptionUpdate(DoctorDescriptionUpdateRequest request) {
        return updateField(request.getId(), doctorEntity -> doctorEntity.setDescription(request.getDescription()));
    }

    public BaseResponse<DoctorResponse> beginningWorkTimeUpdate(DoctorBeginningAndEndingWorkTimeUpdateRequest request) {
        BaseResponse<DoctorResponse> response = getById(request.getId());

        if (response.getData() == null) {
            return response;
        }

        DoctorEntity doctorEntity = doctorConverter.toDoctorEntity(response.getData());
        doctorEntity.setBeginningWorkTime(request.getBeginningWorkTime());
        doctorEntity.setEndingWorkTime(request.getEndingWorkTime());

        DoctorEntity updatedDoctor = doctorRepository.save(doctorEntity);

        return new BaseResponse<>("Success!", 200, doctorConverter.toDoctorResponse(updatedDoctor), 0);
    }

    public BaseResponse<List<DoctorResponse>> getAll(){
        List<DoctorEntity> doctors = doctorRepository.findAll();

        return new BaseResponse<>("Success!", 200, doctorConverter.toDoctorResponse(doctors), 0);
    }
}
