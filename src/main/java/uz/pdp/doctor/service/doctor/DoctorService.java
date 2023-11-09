package uz.pdp.doctor.service.doctor;

import org.springframework.stereotype.Service;
import uz.pdp.doctor.domain.dto.request.booking.BookingRequest;
import uz.pdp.doctor.domain.dto.request.doctor.*;
import uz.pdp.doctor.domain.dto.request.user.UserLastNameUpdateRequest;
import uz.pdp.doctor.domain.dto.request.user.UserNameUpdateRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.booking.BookingResponse;
import uz.pdp.doctor.domain.dto.response.doctor.DoctorResponse;
import uz.pdp.doctor.domain.dto.response.user.UserResponse;
import uz.pdp.doctor.service.BaseService;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService implements BaseService<DoctorRequest, BaseResponse<DoctorResponse>> {
    @Override
    public BaseResponse<DoctorResponse> create(DoctorRequest doctorRequest) {
        return null;
    }

    @Override
    public BaseResponse<DoctorResponse> delete(UUID id) {
        return null;
    }

    @Override
    public BaseResponse<DoctorResponse> getById(UUID id) {
        return null;
    }

    public BaseResponse<DoctorResponse> login(DoctorLoginRequest doctorLoginRequest){
        return null;
    }

    public BaseResponse<DoctorResponse> nameUpdate(DoctorNameUpdateRequest doctorNameUpdateRequest){
        return null;
    }

    public BaseResponse<DoctorResponse> lastNameUpdate(DoctorLastNameUpdateRequest doctorLastNameUpdateRequest){
        return null;
    }

    public BaseResponse<DoctorResponse> specialityUpdate(DoctorLastNameUpdateRequest doctorSpecialityUpdateRequest){
        return null;
    }

    public BaseResponse<DoctorResponse> descriptionUpdate(DoctorDescriptionUpdateRequest doctorDescriptionUpdateRequest){
        return null;
    }

    public BaseResponse<DoctorResponse> beginningWorkTimeUpdate(DoctorBeginningWorkTimeUpdateRequest doctorBeginningWorkTimeUpdateRequest){
        return null;
    }

    public BaseResponse<DoctorResponse> endingWorkTimeUpdate(DoctorEndingWorkTimeUpdateRequest doctorEndingWorkTimeUpdateRequest){
        return null;
    }

    public BaseResponse<List<DoctorResponse>> getAll(){
        return null;
    }
}
