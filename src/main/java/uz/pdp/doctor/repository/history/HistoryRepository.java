package uz.pdp.doctor.repository.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.doctor.domain.entity.history.HistoryEntity;

import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, UUID> {
}
