package uz.pdp.doctor.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.doctor.domain.dto.request.user.UserNameAndLastnameUpdateRequest;
import uz.pdp.doctor.domain.dto.request.user.UserRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.user.UserResponse;
import uz.pdp.doctor.service.user.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/user-interface")
    public ModelAndView userInterface(){
        return new ModelAndView("userInterface");
    }

    @PostMapping("/save")
    public ModelAndView create(
            @Valid @ModelAttribute UserRequest userRequest,
            BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("loginRegister");

        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", extractAllErrors(bindingResult));
        }else {
            BaseResponse<UserResponse> baseResponse = userService.create(userRequest);
            modelAndView.addObject("baseResponse", baseResponse);
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") UUID id){
        BaseResponse<UserResponse> baseResponse = userService.delete(id);

        ModelAndView modelAndView = new ModelAndView("viewName");
        modelAndView.addObject(baseResponse);

        return modelAndView;
    }

    @GetMapping("/get-by-id/{id}")
    public ModelAndView getById(@PathVariable("id") UUID id){
        BaseResponse<UserResponse> baseResponse = userService.getById(id);

        ModelAndView modelAndView = new ModelAndView("viewName");
        modelAndView.addObject(baseResponse);

        return modelAndView;
    }

    @PostMapping("/name-lastname-update")
    public ModelAndView nameAndLastnameUpdate(
            @Valid @ModelAttribute UserNameAndLastnameUpdateRequest userNameAndLastnameUpdateRequest,
            BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("loginRegisterPage");

        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", extractAllErrors(bindingResult));
        }else {
            BaseResponse<UserResponse> baseResponse = userService.nameAndLastUpdate(userNameAndLastnameUpdateRequest);
            modelAndView.addObject("baseResponse", baseResponse);
        }

        return modelAndView;
    }

    @GetMapping("/get-all")
    public ModelAndView getAll(){
        BaseResponse<List<UserResponse>> baseResponse = userService.getAll();

        ModelAndView modelAndView = new ModelAndView("viewName");
        modelAndView.addObject(baseResponse);

        return modelAndView;
    }

    private static String extractAllErrors(BindingResult bindingResult){
        StringBuilder result = new StringBuilder();
        bindingResult.getAllErrors()
                .forEach(error -> result.append(error.getDefaultMessage()).append("\n"));
        return result.toString();
    }
}
