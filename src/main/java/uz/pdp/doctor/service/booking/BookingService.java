package uz.pdp.doctor.service.booking;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.doctor.domain.dto.request.booking.BookingRequest;
import uz.pdp.doctor.domain.dto.response.BaseResponse;
import uz.pdp.doctor.domain.dto.response.booking.BookingResponse;
import uz.pdp.doctor.domain.dto.response.doctor.DoctorResponse;
import uz.pdp.doctor.domain.entity.booking.BookingEntity;
import uz.pdp.doctor.domain.entity.doctor.DoctorEntity;
import uz.pdp.doctor.domain.entity.user.UserEntity;
import uz.pdp.doctor.repository.booking.BookingRepository;
import uz.pdp.doctor.repository.doctor.DoctorRepository;
import uz.pdp.doctor.repository.user.UserRepository;
import uz.pdp.doctor.service.BaseService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService implements BaseService<BookingRequest, BaseResponse<BookingResponse>> {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper mapper;

    @Override
    public BaseResponse<BookingResponse> create(BookingRequest bookingRequest) {
        Optional<UserEntity> user = userRepository.findById(bookingRequest.getUserId());
        Optional<DoctorEntity> doctor = doctorRepository.findById(bookingRequest.getDoctorId());

        if (user.isPresent() && doctor.isPresent()) {
            BookingEntity booking = bookingRepository.save(new BookingEntity(
                    user.get(),
                    doctor.get(),
                    bookingRequest.getBeginningTime(),
                    bookingRequest.getBeginningTime().plusMinutes(30L)));

            return BaseResponse.<BookingResponse>
                            builder()
                    .message("Booking successfully created")
                    .status(200)
                    .data(mapper.map(booking, BookingResponse.class))
                    .build();
        }

        return BaseResponse.<BookingResponse>
                        builder()
                .status(400)
                .message("User/Doctor not found")
                .build();
    }

    @Override
    public BaseResponse<BookingResponse> delete(UUID id) {
        Optional<BookingEntity> booking = bookingRepository.findById(id);

        if (booking.isPresent()) {
            bookingRepository.deleteById(id);

            return BaseResponse.<BookingResponse>
                            builder()
                    .message("Booking successfully deleted")
                    .status(200)
                    .build();
        }

        return BaseResponse.<BookingResponse>
                        builder()
                .message("Booking not found")
                .status(400)
                .build();
    }

    @Override
    public BaseResponse<BookingResponse> getById(UUID id) {
        Optional<BookingEntity> booking = bookingRepository.findById(id);

        if (booking.isPresent()) {
            return BaseResponse.<BookingResponse>
                            builder()
                    .message("Booking has found")
                    .status(200)
                    .data(mapper.map(booking.get(), BookingResponse.class))
                    .build();
        }

        return BaseResponse.<BookingResponse>
                        builder()
                .message("Booking not found")
                .status(400)
                .build();
    }

    public BaseResponse<List<DoctorResponse>> getDoctorsByTheirFreeTime(LocalDateTime beginningTime) {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<DoctorResponse> doctors = new ArrayList<>();

        if (localDateTime.getDayOfYear() <= beginningTime.getDayOfYear()) {
            List<BookingEntity> bookings = bookingRepository.findAll();
            List<DoctorEntity> doctorEntities = doctorRepository.findAll();

            for (DoctorEntity doctorEntity : doctorEntities) {
                if (doctorEntity.getBeginningWorkTime().isBefore(beginningTime.toLocalTime())
                        && doctorEntity.getEndingWorkTime().isAfter(beginningTime.toLocalTime().plusMinutes(30L))) {
                    DoctorResponse doctor = getDoctorByFreeTimeOrNull(doctorEntity, beginningTime, bookings);

                    if (doctor != null) {
                        doctors.add(doctor);
                    }
                }
            }

            return BaseResponse.<List<DoctorResponse>>
                            builder()
                    .message("Doctors which they have free time")
                    .status(200)
                    .data(doctors)
                    .build();
        }

        return BaseResponse.<List<DoctorResponse>>
                        builder()
                .message("The day you selected is inconsistent")
                .status(405)
                .build();
    }

    public DoctorResponse getDoctorByFreeTimeOrNull
            (DoctorEntity doctor, LocalDateTime beginningTime, List<BookingEntity> bookings) {
        boolean isHasFreeTime = false;

        for (BookingEntity booking : bookings) {
            if (booking.getDoctor().getId().equals(doctor.getId())) {
                if (booking.getEndingTime().isBefore(beginningTime) ||
                        beginningTime.plusMinutes(30).isBefore(booking.getBeginningTime())) {
                    isHasFreeTime = true;
                }
            }
        }

        return (isHasFreeTime) ? mapper.map(doctor, DoctorResponse.class) : null;
    }


}
