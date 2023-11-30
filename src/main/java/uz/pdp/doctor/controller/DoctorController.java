package uz.pdp.doctor.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.doctor.domain.dto.request.doctor.*;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.doctor.DoctorResponse;
import uz.pdp.doctor.service.doctor.DoctorService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/save")
    public ModelAndView create(
            @Valid @ModelAttribute ("doctor") DoctorRequest doctorRequest,
            BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("loginRegisterPage");

        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", extractAllErrors(bindingResult));
        }else {
            BaseResponse<DoctorResponse> doctorResponseBaseResponse = doctorService.create(doctorRequest);
            modelAndView.addObject("baseResponse", doctorResponseBaseResponse);
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") UUID id){
        BaseResponse<DoctorResponse> doctorResponse = doctorService.delete(id);

        ModelAndView modelAndView = new ModelAndView("viewName");
        modelAndView.addObject(doctorResponse);

        return modelAndView;
    }

    @GetMapping("/get-by-id/{id}")
    public ModelAndView getById(@PathVariable("id") UUID id){
        BaseResponse<DoctorResponse> doctorResponse = doctorService.getById(id);

        ModelAndView modelAndView = new ModelAndView("viewName");
        modelAndView.addObject(doctorResponse);

        return modelAndView;
    }

    @PostMapping("/name-lastname-update")
    public ModelAndView nameAndLastnameUpdate(
            @Valid @ModelAttribute DoctorNameAndLastnameUpdateRequest doctorNameAndLastnameUpdateRequest,
            BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("viewName");

        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", extractAllErrors(bindingResult));
        }else {
            BaseResponse<DoctorResponse> doctorResponse = doctorService.nameAndLastnameUpdate(doctorNameAndLastnameUpdateRequest);
            modelAndView.addObject("baseResponse", doctorResponse);
        }

        return modelAndView;
    }

    @PostMapping("/speciality-update")
    public ModelAndView specialityUpdate(
            @Valid @ModelAttribute DoctorSpecialityUpdateRequest doctorSpecialityUpdateRequest,
            BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("viewName");

        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", extractAllErrors(bindingResult));
        }else {
            BaseResponse<DoctorResponse> doctorResponse = doctorService.specialityUpdate(doctorSpecialityUpdateRequest);
            modelAndView.addObject("baseResponse", doctorResponse);
        }

        return modelAndView;
    }

    @PostMapping("/description-update")
    public ModelAndView descriptionUpdate(
            @Valid @ModelAttribute DoctorDescriptionUpdateRequest doctorDescriptionUpdateRequest,
            BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("viewName");

        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", extractAllErrors(bindingResult));
        }else {
            BaseResponse<DoctorResponse> doctorResponse = doctorService.descriptionUpdate(doctorDescriptionUpdateRequest);
            modelAndView.addObject("baseResponse", doctorResponse);
        }

        return modelAndView;
    }

    @PostMapping("/beginning-work-time-update")
    public ModelAndView beginningAndEndingWorkTimeUpdate(
            @Valid @ModelAttribute DoctorBeginningAndEndingWorkTimeUpdateRequest doctorBeginningAndEndingWorkTimeUpdateRequest,
            BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("viewName");

        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", extractAllErrors(bindingResult));
        }else {
            BaseResponse<DoctorResponse> doctorResponse = doctorService.beginningWorkTimeUpdate(doctorBeginningAndEndingWorkTimeUpdateRequest);
            modelAndView.addObject("baseResponse", doctorResponse);
        }

        return modelAndView;
    }

    @GetMapping("/get-all")
    public ModelAndView getAll(){
        BaseResponse<List<DoctorResponse>> doctorResponse = doctorService.getAll();

        ModelAndView modelAndView = new ModelAndView("viewName");
        modelAndView.addObject(doctorResponse);

        return modelAndView;
    }

    private static String extractAllErrors(BindingResult bindingResult){
        StringBuilder result = new StringBuilder();
        bindingResult.getAllErrors()
                .forEach(error -> result.append(error.getDefaultMessage()).append("\n"));
        return result.toString();
    }
}
