package uz.pdp.doctor.repository.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.doctor.domain.entity.history.HistoryEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, UUID> {
    List<HistoryEntity> findHistoryEntitiesByUserId(UUID id);
    List<HistoryEntity> findHistoryEntitiesByDoctorId(UUID id);
}
