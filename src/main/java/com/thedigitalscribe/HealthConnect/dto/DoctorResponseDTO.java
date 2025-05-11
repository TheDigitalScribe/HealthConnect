package com.thedigitalscribe.HealthConnect.dto;

import com.thedigitalscribe.HealthConnect.enums.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class DoctorResponseDTO {

    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String specialization;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private OffsetDateTime joiningDate;

}
