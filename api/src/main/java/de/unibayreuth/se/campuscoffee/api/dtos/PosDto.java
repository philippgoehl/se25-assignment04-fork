package de.unibayreuth.se.campuscoffee.api.dtos;

import de.unibayreuth.se.campuscoffee.domain.CampusType;
import de.unibayreuth.se.campuscoffee.domain.PosType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for POS metadata.
 *
 */
@Data
@Builder(toBuilder = true)
public class PosDto {
        @Nullable
        private Long id; // POS id is null when creating or update a new task
        @Nullable
        private LocalDateTime createdAt; // is null when using DTO to create or update a new POS
        @Nullable
        private LocalDateTime updatedAt; // is null when using DTO to create or update a new POS
        @NotBlank
        @Size(max = 255, message = "Name can be at most 255 characters long.")
        private final String name;
        @NotNull
        private final String description;
        @NotNull
        private final PosType type;
        @NotNull
        private final CampusType campus;
        @NotBlank
        private final String street;
        @NotBlank
        private final String houseNumber;
        @NotNull
        private final Integer postalCode;
        @NotBlank
        private final String city;
}
