package uz.pdp.doctor.service.booking;


import org.springframework.stereotype.Service;
import uz.pdp.doctor.domain.dto.request.booking.BookingRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.booking.BookingResponse;
import uz.pdp.doctor.service.BaseService;

import java.util.UUID;

@Service
public class BookingService implements BaseService<BookingRequest, BaseResponse<BookingResponse>> {

    @Override
    public BaseResponse<BookingResponse> create(BookingRequest bookingRequest) {
        return null;
    }

    @Override
    public BaseResponse<BookingResponse> delete(UUID id) {
        return null;
    }

    @Override
    public BaseResponse<BookingResponse> getById(UUID id) {
        return null;
    }


}
