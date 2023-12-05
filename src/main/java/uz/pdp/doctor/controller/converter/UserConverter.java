package uz.pdp.doctor.controller.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.doctor.domain.dto.request.user.UserRequest;
import uz.pdp.doctor.domain.dto.response.user.UserResponse;
import uz.pdp.doctor.domain.entity.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;
//    private final PasswordEncoder passwordEncoder;

    public UserEntity toUserEntity(UserRequest userRequest){
        UserEntity user = modelMapper.map(userRequest, UserEntity.class);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public UserResponse toUserResponse(UserEntity user){
        return modelMapper.map(user, UserResponse.class);
    }

    public List<UserResponse> toUserResponse(List<UserEntity> users){
        return modelMapper.map(users,
                new TypeToken<List<UserResponse>>() {}.getType());
    }

    public UserEntity toUserEntity(UserResponse userResponse){
        return modelMapper.map(userResponse, UserEntity.class);
    }

}
