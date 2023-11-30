package uz.pdp.doctor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.doctor.domain.dto.request.login.UserAndDoctorLoginRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.doctor.DoctorResponse;
import uz.pdp.doctor.domain.dto.response.user.UserResponse;
import uz.pdp.doctor.service.doctor.DoctorService;
import uz.pdp.doctor.service.user.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final DoctorService doctorService;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView view) {
        view.setViewName("loginRegister");
        return view;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestBody UserAndDoctorLoginRequest loginRequest,
                              BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("loginRegister");

        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", extractAllErrors(bindingResult));
        }else {
            BaseResponse<UserResponse> baseResponse = userService.login(loginRequest);

            if (baseResponse.getData() == null){
                BaseResponse<DoctorResponse> login = doctorService.login(loginRequest);
                modelAndView.addObject("baseResponse", login);
                if (login.getData() != null){
                    modelAndView.setViewName("doctorInterface");
                }

            }else {
                modelAndView.addObject("baseResponse", baseResponse);
                modelAndView.setViewName("userInterface");
            }
        }

        return modelAndView;
    }

    private static String extractAllErrors(BindingResult bindingResult){
        StringBuilder result = new StringBuilder();
        bindingResult.getAllErrors()
                .forEach(error -> result.append(error.getDefaultMessage()).append("\n"));
        return result.toString();
    }
}
