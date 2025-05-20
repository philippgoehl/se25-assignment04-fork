package de.unibayreuth.se.campuscoffee.domain.ports;

import de.unibayreuth.se.campuscoffee.domain.Pos;
import de.unibayreuth.se.campuscoffee.domain.exceptions.PosNotFoundException;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * Interface for the implementation of the POS data service that the domain layer provides as a port.
 */
public interface PosDataService {
    void clear();
    @NonNull
    List<Pos> getAll();
    Optional<Pos> getById(@NonNull Long id) throws PosNotFoundException;
    Optional<Pos> getByName(@NonNull String name) throws PosNotFoundException;
    @NonNull
    Pos upsert(@NonNull Pos pos) throws PosNotFoundException;
}
