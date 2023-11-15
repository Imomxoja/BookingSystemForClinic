package uz.pdp.doctor.controller.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.doctor.domain.dto.request.booking.BookingRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.booking.BookingResponse;
import uz.pdp.doctor.service.booking.BookingService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/add")
    public ModelAndView createBooking(@ModelAttribute BookingRequest bookingRequest) {
        ModelAndView view = new ModelAndView();

        BaseResponse<BookingResponse> response = bookingService.create(bookingRequest);

        view.addObject("message", response.getMessage());

        return view;
    }
}
