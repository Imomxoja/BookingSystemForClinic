package uz.pdp.doctor.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.doctor.controller.converter.UserConverter;
import uz.pdp.doctor.domain.dto.request.booking.BookingRequest;
import uz.pdp.doctor.domain.dto.request.user.UserLastNameUpdateRequest;
import uz.pdp.doctor.domain.dto.request.user.UserLoginRequest;
import uz.pdp.doctor.domain.dto.request.user.UserNameUpdateRequest;
import uz.pdp.doctor.domain.dto.request.user.UserRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.booking.BookingResponse;
import uz.pdp.doctor.domain.dto.response.user.UserResponse;
import uz.pdp.doctor.domain.entity.user.UserEntity;
import uz.pdp.doctor.domain.entity.user.UserRole;
import uz.pdp.doctor.repository.user.UserRepository;
import uz.pdp.doctor.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserRequest, BaseResponse<UserResponse>> {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<UserResponse> create(UserRequest userRequest) {
        UserEntity userEntity = userConverter.toUserEntity(userRequest);
        userEntity.setRole(UserRole.USER);

        userRepository.save(userEntity);

        return new BaseResponse<>(
                "Success!",
                200, userConverter.toUserResponse(userEntity));
    }

    @Override
    public BaseResponse<UserResponse> delete(UUID id) {
        BaseResponse<UserResponse> baseResponse = getById(id);

        if (baseResponse.getData() == null){
            return baseResponse;
        }

        UserEntity userEntity = userConverter.toUserEntity(baseResponse.getData());
        userRepository.delete(userEntity);

        return new BaseResponse<>("Success!", 200, null);
    }

    @Override
    public BaseResponse<UserResponse> getById(UUID id) {
        Optional<UserEntity> optional = userRepository.findById(id);

        return optional.map(user -> new BaseResponse<>(
                "Success!",
                200, userConverter.toUserResponse(user)))
                .orElseGet(() -> new BaseResponse<>("User not found!", 404, null));

    }

    public BaseResponse<UserResponse> findByEmail(String email){
        Optional<UserEntity> optional = userRepository.findUserEntityByEmail(email);

        return optional.map(userEntity -> new BaseResponse<>(
                "Success!",
                200, userConverter.toUserResponse(userEntity)))
                .orElseGet(() -> new BaseResponse<>("User not found!", 404, null));
    }

    public BaseResponse<UserResponse> login(UserLoginRequest userLoginRequest){
        BaseResponse<UserResponse> baseResponse = findByEmail(userLoginRequest.getEmail());

        if (baseResponse.getData() == null){
            return baseResponse;
        }

        UserEntity userEntity = userConverter.toUserEntity(baseResponse.getData());

        if (passwordEncoder.matches(userLoginRequest.getPassword(), userEntity.getPassword())){
            return new BaseResponse<>("Success!", 200, userConverter.toUserResponse(userEntity));
        }
        return new BaseResponse<>("Something went wrong!", 400, null);
    }

    public BaseResponse<UserResponse> nameUpdate(UserNameUpdateRequest userNameUpdateRequest){
        return updateUser(userNameUpdateRequest, "name");
    }

    public BaseResponse<UserResponse> lastNameUpdate(UserLastNameUpdateRequest userLastNameUpdateRequest){
        return updateUser(userLastNameUpdateRequest, "lastname");
    }

    public BaseResponse<UserResponse> updateUser(Object request, String whatUserField){

        UserNameUpdateRequest userUpdateRequest = (UserNameUpdateRequest) request;
        BaseResponse<UserResponse> baseResponse = getById(userUpdateRequest.getId());

        if (baseResponse.getData() == null){
            return baseResponse;
        }

        UserEntity userEntity = userConverter.toUserEntity(baseResponse.getData());

        if (whatUserField.equals("name")){
            userEntity.setName(userEntity.getLastName());
        }

        if (whatUserField.equals("lastname")){
            userEntity.setLastName(userEntity.getLastName());
        }

        UserEntity updatedUser = userRepository.save(userEntity);

        return new BaseResponse<>("Success!", 200, userConverter.toUserResponse(updatedUser));


    }

    public BaseResponse<List<UserResponse>> getAll(){
        List<UserEntity> allUsers = userRepository.findAll();

        return new BaseResponse<>("Success!", 200, userConverter.toUserResponse(allUsers));
    }

}
