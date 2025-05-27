package com.thedigitalscribe.HealthConnect.repository;

import com.thedigitalscribe.HealthConnect.enums.Role;
import com.thedigitalscribe.HealthConnect.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Page<User> findByRole(Role role, Pageable pageable);

    Page<User> findByCreatedAtBetween(Instant startDate, Instant endDate, Pageable pageable);

    Page<User> findByUpdatedAtBetween(Instant startDate, Instant endDate, Pageable pageable);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :usernameKeyword, '%'))")
    Page<User> findByUsernameContainingIgnoreCase(@Param("usernameKeyword") String usernameKeyword, Pageable pageable);

    @Query("SELECT u FROM User u WHERE " +
            "(:role IS NULL OR u.role = :role) AND " +
            "(:usernameKeyword IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :usernameKeyword, '%')))")
    Page<User> searchUsersByCriteria(@Param("role") Role role,
                                     @Param("usernameKeyword") String usernameKeyword,
                                     Pageable pageable);

}
