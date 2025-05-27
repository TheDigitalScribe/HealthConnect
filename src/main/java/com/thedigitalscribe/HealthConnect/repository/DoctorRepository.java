package com.thedigitalscribe.HealthConnect.repository;

import com.thedigitalscribe.HealthConnect.enums.Gender;
import com.thedigitalscribe.HealthConnect.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Optional<Doctor> findByPhoneNumber(String phoneNumber);

    Optional<Doctor> findByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    Page<Doctor> findBySpecializationAndCity(String specialization, String city, Pageable pageable);

    Page<Doctor> findByCity(String city, Pageable pageable);

    Page<Doctor> findByGender(Gender gender, Pageable pageable);

    Page<Doctor> findBySpecialization(String specialization, Pageable pageable);

    @Query("SELECT d FROM Doctor d WHERE d.active = true AND " +
            "(LOWER(d.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(d.lastName) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<Doctor> searchByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Query("SELECT d FROM Doctor d WHERE d.active = true AND " +
            "LOWER(CONCAT(d.firstName, ' ', d.lastName)) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    Page<Doctor> searchByFullNameContainingIgnoreCase(@Param("fullName") String fullName, Pageable pageable);

    @Query("SELECT d FROM Doctor d WHERE d.active = true AND " +
            "(:specialization IS NULL OR d.specialization = :specialization) AND " +
            "(:city IS NULL OR d.city = :city) AND " +
            "(:gender IS NULL OR d.gender = :gender)")
    Page<Doctor> searchDoctors(@Param("specialization") String specialization,
                               @Param("city") String city,
                               @Param("gender") Gender gender,
                               Pageable pageable);

    Page<Doctor> findByJoiningDateAfter(Instant joiningDate, Pageable pageable);

    Page<Doctor> findByJoiningDateBefore(Instant joiningDate, Pageable pageable);

    Page<Doctor> findByJoiningDateBetween(Instant startDate, Instant endDate, Pageable pageable);

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

}
