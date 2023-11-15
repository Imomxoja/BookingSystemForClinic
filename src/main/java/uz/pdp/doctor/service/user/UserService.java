package uz.pdp.doctor.service.user;

import org.springframework.stereotype.Service;
import uz.pdp.doctor.domain.dto.request.user.UserLastNameUpdateRequest;
import uz.pdp.doctor.domain.dto.request.user.UserLoginRequest;
import uz.pdp.doctor.domain.dto.request.user.UserNameUpdateRequest;
import uz.pdp.doctor.domain.dto.request.user.UserRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.user.UserResponse;
import uz.pdp.doctor.service.BaseService;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements BaseService<UserRequest, BaseResponse<UserResponse>> {

    @Override
    public BaseResponse<UserResponse> create(UserRequest userRequest) {
        return null;
    }

    @Override
    public BaseResponse<UserResponse> delete(UUID id) {
        return null;
    }

    @Override
    public BaseResponse<UserResponse> getById(UUID id) {
        return null;
    }

    public BaseResponse<UserResponse> login(UserLoginRequest userLoginRequest){
        return null;
    }

    public BaseResponse<UserResponse> nameUpdate(UserNameUpdateRequest userNameUpdateRequest){
        return null;
    }

    public BaseResponse<UserResponse> lastNameUpdate(UserLastNameUpdateRequest userLastNameUpdateRequest){
        return null;
    }

    public BaseResponse<List<UserResponse>> getAll(){
        return null;
    }

}
