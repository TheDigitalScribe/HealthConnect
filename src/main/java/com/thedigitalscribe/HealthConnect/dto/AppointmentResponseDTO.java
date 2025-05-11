package com.thedigitalscribe.HealthConnect.dto;

import com.thedigitalscribe.HealthConnect.enums.AppointmentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class AppointmentResponseDTO {

    private UUID id;
    private UUID patientId;
    private String patientName;
    private UUID doctorId;
    private String doctorName;
    private Instant appointmentDateTime;
    private Instant appointmentEndTime;
    private AppointmentStatus appointmentStatus;
    private String reason;
    private String notes;

}