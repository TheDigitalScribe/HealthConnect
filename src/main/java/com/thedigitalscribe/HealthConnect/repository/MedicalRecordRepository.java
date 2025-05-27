package com.thedigitalscribe.HealthConnect.repository;

import com.thedigitalscribe.HealthConnect.model.Appointment;
import com.thedigitalscribe.HealthConnect.model.Doctor;
import com.thedigitalscribe.HealthConnect.model.MedicalRecord;
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
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    Optional<MedicalRecord> findByAppointment(Appointment appointment);

    Optional<MedicalRecord> findByAppointment_Id(UUID appointmentId);

    Page<MedicalRecord> findByAppointment_Doctor(Doctor doctor, Pageable pageable);

    Page<MedicalRecord> findByAppointment_Doctor_Id(UUID doctorId, Pageable pageable);

    Page<MedicalRecord> findByDiagnosisContainingIgnoreCase(String diagnosisKeyword, Pageable pageable);

    Page<MedicalRecord> findBySymptomsContainingIgnoreCase(String symptomKeyword, Pageable pageable);

    Page<MedicalRecord> findByNotesContainingIgnoreCase(String noteKeyword, Pageable pageable);

    Page<MedicalRecord> findByLabResultsContainingIgnoreCase(String labResultKeyword, Pageable pageable);

    Page<MedicalRecord> findByCreatedAtBetween(Instant startDate, Instant endDate, Pageable pageable);

    Page<MedicalRecord> findByUpdatedAtBetween(Instant startDate, Instant endDate, Pageable pageable);

    Page<MedicalRecord> findByAppointment_Patient_IdAndDiagnosisContainingIgnoreCase(
            UUID patientId, String diagnosisKeyword, Pageable pageable);

    Page<MedicalRecord> findByAppointment_Patient_IdAndCreatedAtBetween(
            UUID patientId, Instant startDate, Instant endDate, Pageable pageable);


    Page<MedicalRecord> findByAppointment_Doctor_IdAndCreatedAtBetween(
            UUID doctorId, Instant startDate, Instant endDate, Pageable pageable);

    @Query("SELECT mr FROM MedicalRecord mr " +
            "WHERE mr.appointment.patient.id = :patientId " +
            "AND LOWER(mr.diagnosis) LIKE LOWER(CONCAT('%', :diagnosis, '%')) " +
            "AND mr.createdAt >= :startDate")
    Page<MedicalRecord> findByPatientIdAndDiagnosisContainingAndCreatedAfter(
            @Param("patientId") UUID patientId,
            @Param("diagnosis") String diagnosis,
            @Param("startDate") Instant startDate,
            Pageable pageable);

}
