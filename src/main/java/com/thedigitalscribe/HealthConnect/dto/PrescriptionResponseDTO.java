package com.thedigitalscribe.HealthConnect.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class PrescriptionResponseDTO {

    private UUID id;
    private String medicineName;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;
    private UUID medicalRecordId;
    private String diagnosis;

}
