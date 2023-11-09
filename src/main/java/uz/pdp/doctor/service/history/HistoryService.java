package uz.pdp.doctor.service.history;

import org.springframework.stereotype.Service;
import uz.pdp.doctor.domain.dto.request.booking.BookingRequest;
import uz.pdp.doctor.domain.dto.request.history.HistoryRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.booking.BookingResponse;
import uz.pdp.doctor.domain.dto.response.history.HistoryResponse;
import uz.pdp.doctor.service.BaseService;

import java.util.UUID;

@Service
public class HistoryService implements BaseService<HistoryRequest, BaseResponse<HistoryResponse>> {

    @Override
    public BaseResponse<HistoryResponse> create(HistoryRequest historyRequest) {
        return null;
    }

    @Override
    public BaseResponse<HistoryResponse> delete(UUID id) {
        return null;
    }

    @Override
    public BaseResponse<HistoryResponse> getById(UUID id) {
        return null;
    }

    public BaseResponse<HistoryResponse> getAll(){
        return null;
    }

    public BaseResponse<HistoryResponse> getAllByUserId(UUID id){
        return null;
    }

    public BaseResponse<HistoryResponse> getAllByDoctorId(UUID id){
        return null;
    }
}
