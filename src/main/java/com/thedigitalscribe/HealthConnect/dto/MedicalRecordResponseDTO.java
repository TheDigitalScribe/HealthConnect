package com.thedigitalscribe.HealthConnect.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class MedicalRecordResponseDTO {

    private UUID id;
    private UUID appointmentId;
    private Instant appointmentDateTime;
    private String diagnosis;
    private String symptoms;
    private String notes;
    private String labResults;
    private List<PrescriptionResponseDTO> prescriptions;

}
