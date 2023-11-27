package uz.pdp.doctor.repository.booking;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.doctor.domain.entity.booking.BookingEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    @Query("select b from bookings b where b.isActive = true")
    Page<BookingEntity> findBookingEntitiesByIsActive(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update bookings b set b.isActive = false where b.id = :id")
    void updateBookingActivity(@Param("id") UUID bookingId);
}
