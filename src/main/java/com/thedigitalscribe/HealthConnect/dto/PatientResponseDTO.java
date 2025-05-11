package com.thedigitalscribe.HealthConnect.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class PatientResponseDTO {

    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String gender;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
