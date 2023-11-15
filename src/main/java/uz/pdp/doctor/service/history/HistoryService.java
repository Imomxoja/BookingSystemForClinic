package uz.pdp.doctor.service.history;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import uz.pdp.doctor.domain.dto.request.history.HistoryRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.history.HistoryResponse;
import uz.pdp.doctor.domain.entity.history.HistoryEntity;
import uz.pdp.doctor.repository.history.HistoryRepository;
import uz.pdp.doctor.service.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService implements BaseService<HistoryRequest, BaseResponse<HistoryResponse>> {
    private final HistoryRepository historyRepository;
    private final ModelMapper mapper;

    @Override
    public BaseResponse<HistoryResponse> create(HistoryRequest historyRequest) {
        HistoryEntity history = historyRepository.save(mapper.map(historyRequest, HistoryEntity.class));

        return BaseResponse.<HistoryResponse>
                builder()
                .message("History successfully created")
                .status(200)
                .data(mapper.map(history, HistoryResponse.class))
                .build();
    }

    @Override
    public BaseResponse<HistoryResponse> delete(UUID id) {
        Optional<HistoryEntity> history = historyRepository.findById(id);

        if (history.isPresent()) {
            historyRepository.deleteById(id);

            return BaseResponse.<HistoryResponse>
                            builder()
                    .message("History successfully deleted")
                    .status(200)
                    .build();
        }

        return BaseResponse.<HistoryResponse>
                        builder()
                .message("History not found")
                .status(400)
                .build();
    }

    @Override
    public BaseResponse<HistoryResponse> getById(UUID id) {
        Optional<HistoryEntity> history = historyRepository.findById(id);

        if (history.isPresent()) {
            return BaseResponse.<HistoryResponse>
                            builder()
                    .message("history has found")
                    .status(200)
                    .data(mapper.map(history.get(), HistoryResponse.class))
                    .build();
        }
        return BaseResponse.<HistoryResponse>
                        builder()
                .message("history not found")
                .status(400)
                .build();
    }

    public BaseResponse<List<HistoryResponse>> getAll() {
        List<HistoryEntity> histories = historyRepository.findAll();

        return BaseResponse.<List<HistoryResponse>>
                        builder()
                .message("all histories")
                .status(200)
                .data(mapper.map(histories,
                        new TypeToken<List<HistoryResponse>>() {
                        }.getType()))
                .build();
    }

    public BaseResponse<List<HistoryResponse>> getAllByUserId(UUID id) {
        List<HistoryEntity> userHistories = historyRepository.findHistoryEntitiesByUserId(id);

        return BaseResponse.<List<HistoryResponse>>
                        builder()
                .message("all user histories")
                .status(200)
                .data(mapper.map(userHistories,
                        new TypeToken<List<HistoryResponse>>() {
                        }.getType()))
                .build();
    }

    public BaseResponse<List<HistoryResponse>> getAllByDoctorId(UUID id) {
        List<HistoryEntity> doctorHistories = historyRepository.findHistoryEntitiesByDoctorId(id);

        return BaseResponse.<List<HistoryResponse>>
                        builder()
                .message("all doctor histories")
                .status(200)
                .data(mapper.map(doctorHistories,
                        new TypeToken<List<HistoryResponse>>() {
                        }.getType()))
                .build();
    }
}
