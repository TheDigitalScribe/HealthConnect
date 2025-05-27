package com.thedigitalscribe.HealthConnect.repository;

import com.thedigitalscribe.HealthConnect.enums.Gender;
import com.thedigitalscribe.HealthConnect.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    Optional<Patient> findByPhoneNumber(String phoneNumber);

    Optional<Patient> findByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    @Query("SELECT p FROM Patient p WHERE " +
            "LOWER(p.firstName) LIKE LOWER(CONCAT('%', :nameKeyword, '%')) OR " +
            "LOWER(p.lastName) LIKE LOWER(CONCAT('%', :nameKeyword, '%'))")
    Page<Patient> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@Param("nameKeyword") String nameKeyword, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE " +
            "LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE LOWER(CONCAT('%', :fullNameKeyword, '%'))")
    Page<Patient> findByFullNameContainingIgnoreCase(@Param("fullNameKeyword") String fullNameKeyword, Pageable pageable);

    Page<Patient> findByCity(String city, Pageable pageable);

    Page<Patient> findByState(String state, Pageable pageable);

    Page<Patient> findByCountry(String country, Pageable pageable);

    Page<Patient> findByGender(Gender gender, Pageable pageable);

    Page<Patient> findByDobBetween(LocalDate startDob, LocalDate endDob, Pageable pageable);

    Page<Patient> findByCreatedAtAfter(Instant createdAt, Pageable pageable);

    Page<Patient> findByCreatedAtBetween(Instant startDate, Instant endDate, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE " +
            "(:firstNameKeyword IS NULL OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstNameKeyword, '%'))) AND " +
            "(:lastNameKeyword IS NULL OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastNameKeyword, '%'))) AND " +
            "(:dob IS NULL OR p.dob = :dob) AND " +
            "(:gender IS NULL OR p.gender = :gender) AND " +
            "(:city IS NULL OR p.city = :city) AND " +
            "(:state IS NULL OR p.state = :state)")
    Page<Patient> searchPatientsByCriteria(@Param("firstNameKeyword") String firstNameKeyword,
                                           @Param("lastNameKeyword") String lastNameKeyword,
                                           @Param("dob") LocalDate dob,
                                           @Param("gender") Gender gender,
                                           @Param("city") String city,
                                           @Param("state") String state,
                                           Pageable pageable);

}
