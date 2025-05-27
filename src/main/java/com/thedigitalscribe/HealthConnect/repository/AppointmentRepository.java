package com.thedigitalscribe.HealthConnect.repository;

import com.thedigitalscribe.HealthConnect.enums.AppointmentStatus;
import com.thedigitalscribe.HealthConnect.model.Appointment;
import com.thedigitalscribe.HealthConnect.model.Doctor;
import com.thedigitalscribe.HealthConnect.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    Page<Appointment> findByPatient(Patient patient, Pageable pageable);

    Page<Appointment> findByDoctor(Doctor doctor, Pageable pageable);

    Page<Appointment> findByAppointmentStatus(AppointmentStatus appointmentStatus, Pageable pageable);

    Page<Appointment> findByPatient_Id(UUID patientId, Pageable pageable);

    Page<Appointment> findByDoctor_Id(UUID doctorId, Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE  " +
            "a.appointmentDateTime >= :startDate AND a.appointmentDateTime <= :endDate")
    Page<Appointment> findByAppointmentDateTimeBetween(@Param("startDate") Instant startDate,
                                                       @Param("endDate") Instant endDate,
                                                       Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor AND " +
            "a.appointmentDateTime >= :startDate AND a.appointmentDateTime <= :endDate")
    Page<Appointment> findByDoctorAndAppointmentDateTimeBetween(@Param("doctor") Doctor doctor,
                                                                @Param("startDate") Instant startDate,
                                                                @Param("endDate") Instant endDate,
                                                                Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.patient = :patient AND " +
            "a.appointmentDateTime >= :startDate AND a.appointmentDateTime <= :endDate")
    Page<Appointment> findByPatientAndAppointmentDateTimeBetween(@Param("patient") Patient patient,
                                                                 @Param("startDate") Instant startDate,
                                                                 @Param("endDate") Instant endDate,
                                                                 Pageable pageable);

    boolean existsByDoctorAndAppointmentDateTimeBeforeAndAppointmentEndTimeAfter(Doctor doctor, Instant proposedEndTime, Instant proposedStartTime);

    boolean existsByPatientAndAppointmentDateTimeBeforeAndAppointmentEndTimeAfter(Patient patient, Instant proposedEndTime, Instant proposedStartTime);

    Page<Appointment> findByPatientAndAppointmentStatus(Patient patient, AppointmentStatus appointmentStatus, Pageable pageable);

    Page<Appointment> findByDoctorAndAppointmentStatus(Doctor doctor, AppointmentStatus appointmentStatus, Pageable pageable);

    Page<Appointment> findByDoctorAndAppointmentStatusAndAppointmentDateTimeAfter(Doctor doctor, AppointmentStatus appointmentStatus, Instant appointmentDateTime, Pageable pageable);

}

