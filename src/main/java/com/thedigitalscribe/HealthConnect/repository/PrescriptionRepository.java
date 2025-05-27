package com.thedigitalscribe.HealthConnect.repository;

import com.thedigitalscribe.HealthConnect.model.Doctor;
import com.thedigitalscribe.HealthConnect.model.MedicalRecord;
import com.thedigitalscribe.HealthConnect.model.Patient;
import com.thedigitalscribe.HealthConnect.model.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface PrescriptionRepository {

    Page<Prescription> findByMedicalRecord(MedicalRecord medicalRecord, Pageable pageable);

    Page<Prescription> findByMedicalRecord_Id(UUID medicalRecordId, Pageable pageable);

    Page<Prescription> findByMedicalRecord_Appointment_Patient(Patient patient, Pageable pageable);

    Page<Prescription> findByMedicalRecord_Appointment_Patient_Id(UUID patientId, Pageable pageable);

    Page<Prescription> findByMedicalRecord_Appointment_Doctor(Doctor doctor, Pageable pageable);

    Page<Prescription> findByMedicalRecord_Appointment_Doctor_Id(UUID doctorId, Pageable pageable);

    Page<Prescription> findByMedicineNameContainingIgnoreCase(String medicineNameKeyword, Pageable pageable);

    Page<Prescription> findByInstructionsContainingIgnoreCase(String instructionsKeyword, Pageable pageable);

    Page<Prescription> findByDosage(String dosage, Pageable pageable);

    Page<Prescription> findByFrequency(String frequency, Pageable pageable);

    Page<Prescription> findByDuration(String duration, Pageable pageable);

    Page<Prescription> findByCreatedAtBetween(Instant startDate, Instant endDate, Pageable pageable);

    Page<Prescription> findByMedicalRecord_Appointment_Patient_IdAndMedicineNameContainingIgnoreCase(
            UUID patientId, String medicineNameKeyword, Pageable pageable);

    Page<Prescription> findByMedicalRecord_Appointment_Doctor_IdAndCreatedAtBetween(
            UUID doctorId, Instant startDate, Instant endDate, Pageable pageable);

    @Query("SELECT p FROM Prescription p WHERE p.medicalRecord.appointment.patient.id = :patientId " +
            "ORDER BY p.createdAt DESC")
    Page<Prescription> findByPatientIdOrderByCreatedAtDesc(@Param("patientId") UUID patientId, Pageable pageable);

}
