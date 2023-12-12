package uz.pdp.doctor.controller;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.doctor.domain.dto.request.booking.BookingRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.booking.BookingResponse;
import uz.pdp.doctor.service.booking.BookingService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/add")
//    @PreAuthorize("hasAnyRole('USER')")
    public ModelAndView createBooking(@ModelAttribute BookingRequest bookingRequest) {
        ModelAndView view = new ModelAndView();

        BaseResponse<BookingResponse> response = bookingService.create(bookingRequest);
        BaseResponse<List<BookingResponse>> all = bookingService.findAll(0);

        view.addObject("message", response.getMessage());
        view.addObject("bookings", all.getData());
        view.addObject("pages", all.getTotalPage());

        return view;
    }

//    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/delete/{id}")
    public ModelAndView deleteBooking(@PathVariable("id") UUID bookingId) {
        ModelAndView view = new ModelAndView();

        BaseResponse<BookingResponse> response = bookingService.delete(bookingId);
        BaseResponse<List<BookingResponse>> all = bookingService.findAll(0);

        view.addObject("message", response.getMessage());
        view.addObject("pages", all.getTotalPage());
        view.addObject("bookings", all.getData());

        return view;
    }

    @GetMapping("/get-all")
    public ModelAndView getAllBookings(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber) {
        ModelAndView view = new ModelAndView();

        BaseResponse<List<BookingResponse>> response = bookingService.findAll(pageNumber);

        view.addObject("bookings", response.getData());
        view.addObject("pages", response.getTotalPage());

        return view;
    }


}
