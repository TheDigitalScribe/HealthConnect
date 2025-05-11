package com.thedigitalscribe.HealthConnect.dto;

import com.thedigitalscribe.HealthConnect.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserResponseDTO {

    private UUID id;
    private String username;
    private Role role;

}