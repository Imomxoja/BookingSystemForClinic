package uz.pdp.doctor.service.user;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.doctor.controller.converter.UserConverter;
import uz.pdp.doctor.domain.dto.request.login.UserAndDoctorLoginRequest;
import uz.pdp.doctor.domain.dto.request.user.UserNameAndLastnameUpdateRequest;
import uz.pdp.doctor.domain.dto.request.user.UserRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.user.UserResponse;
import uz.pdp.doctor.domain.entity.user.UserEntity;
import uz.pdp.doctor.domain.entity.user.UserRole;
import uz.pdp.doctor.repository.user.UserRepository;
import uz.pdp.doctor.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserRequest, BaseResponse<UserResponse>> {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<UserResponse> create(UserRequest userRequest) {
        UserEntity userEntity = userConverter.toUserEntity(userRequest);
        userEntity.setRole(UserRole.USER);

        BaseResponse<UserResponse> byEmail = findByEmail(userRequest.getEmail());
        if (byEmail.getData() != null){
            return new BaseResponse<>(
                    "email bor",
                    200, null, 0);
        }

        userRepository.save(userEntity);

        return new BaseResponse<>(
                "Success!",
                200, userConverter.toUserResponse(userEntity), 0);
    }

    @Override
    public BaseResponse<UserResponse> delete(UUID id) {
        BaseResponse<UserResponse> baseResponse = getById(id);

        if (baseResponse.getData() == null){
            return baseResponse;
        }

        userRepository.deleteById(id);

        return new BaseResponse<>("Success!", 200, null, 0);
    }

    @Override
    public BaseResponse<UserResponse> getById(UUID id) {
        Optional<UserEntity> optional = userRepository.findById(id);

        return optional.map(user -> new BaseResponse<>(
                "Success!",
                200, userConverter.toUserResponse(user), 0))
                .orElseGet(() -> new BaseResponse<>("User not found!", 404, null, 0));

    }

    public BaseResponse<UserResponse> findByEmail(String email){
        Optional<UserEntity> optional = userRepository.findUserEntityByEmail(email);

        return optional.map(userEntity -> new BaseResponse<>(
                "Success!",
                200, userConverter.toUserResponse(userEntity), 0))
                .orElseGet(() -> new BaseResponse<>("User not found!", 404, null, 0));
    }

    public BaseResponse<UserResponse> login(UserAndDoctorLoginRequest userLoginRequest){
        BaseResponse<UserResponse> baseResponse = findByEmail(userLoginRequest.getEmail());

        if (baseResponse.getData() == null){
            return baseResponse;
        }

        UserEntity userEntity = userConverter.toUserEntity(baseResponse.getData());
        if (userEntity.getPassword().equals(userLoginRequest.getPassword())){
            return new BaseResponse<>("Success!", 200, userConverter.toUserResponse(userEntity), 0);
        }
//        if (passwordEncoder.matches(userLoginRequest.getPassword(), userEntity.getPassword())){
//            return new BaseResponse<>("Success!", 200, userConverter.toUserResponse(userEntity), 0);
//        }
        return new BaseResponse<>("Something went wrong!", 400, null, 0);
    }

    public BaseResponse<UserResponse> updateField(UUID id, Consumer<UserEntity> updateField){
        BaseResponse<UserResponse> response = getById(id);

        if (response.getData() == null){
            return response;
        }

        UserEntity userEntity = userConverter.toUserEntity(response.getData());
        updateField.accept(userEntity);

        UserEntity updatedUser = userRepository.save(userEntity);

        return new BaseResponse<>("Success!", 200, userConverter.toUserResponse(updatedUser), 0);
    }

    public BaseResponse<UserResponse> nameAndLastUpdate(UserNameAndLastnameUpdateRequest userNameAndLastnameUpdateRequest){
        BaseResponse<UserResponse> response = getById(userNameAndLastnameUpdateRequest.getId());

        if (response.getData() == null){
            return response;
        }

        UserEntity userEntity = userConverter.toUserEntity(response.getData());
        userEntity.setName(userNameAndLastnameUpdateRequest.getName());
        userEntity.setLastName(userNameAndLastnameUpdateRequest.getLastName());

        UserEntity updatedUser = userRepository.save(userEntity);

        return new BaseResponse<>("Success!", 200, userConverter.toUserResponse(updatedUser), 0);
    }

    public BaseResponse<List<UserResponse>> getAll(){
        List<UserEntity> allUsers = userRepository.findAll();

        return new BaseResponse<>("Success!", 200, userConverter.toUserResponse(allUsers), 0);
    }

}
