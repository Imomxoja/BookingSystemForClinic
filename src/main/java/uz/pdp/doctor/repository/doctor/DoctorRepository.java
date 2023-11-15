package uz.pdp.doctor.repository.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.doctor.domain.entity.doctor.DoctorEntity;


import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;

import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, UUID> {

    Optional<DoctorEntity> findDoctorEntitiesByEmail(String email);


}
