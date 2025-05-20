package de.unibayreuth.se.campuscoffee.domain;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Domain class that stores the POS metadata.
 */
@Data
public class Pos implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Nullable
        private Long id; // null when POS has not been created yet
        @Nullable
        private LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("UTC")); // set on POS creation
        @Nullable
        private LocalDateTime updatedAt = createdAt; // set on POS creation and update
        @NonNull
        private String name;
        @NonNull
        private String description;
        @NonNull
        private PosType type;
        @NonNull
        private CampusType campus;
        @NonNull
        private String street;
        @NonNull
        private String houseNumber;
        @NonNull
        private Integer postalCode;
        @NonNull
        private String city;
}
