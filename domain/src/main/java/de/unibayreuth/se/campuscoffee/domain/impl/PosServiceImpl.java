package de.unibayreuth.se.campuscoffee.domain.impl;

import de.unibayreuth.se.campuscoffee.domain.Pos;
import de.unibayreuth.se.campuscoffee.domain.exceptions.PosNotFoundException;
import de.unibayreuth.se.campuscoffee.domain.ports.PosDataService;
import de.unibayreuth.se.campuscoffee.domain.ports.PosService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PosServiceImpl implements PosService {
    private final PosDataService posDataService;

    @Override
    public void clear() {
        posDataService.clear();
    }

    @Override
    @NonNull
    public List<Pos> getAll() {
        return posDataService.getAll();
    }

    @Override
    @NonNull
    public Pos getByName(@NonNull String name) throws PosNotFoundException {
        return verifyPosExists(name);
    }

    @Override
    @NonNull
    public Pos getById(@NonNull Long id) throws PosNotFoundException {
        return verifyPosExists(id);
    }

    @Override
    @NonNull
    public Pos upsert(@NonNull Pos pos) throws PosNotFoundException {
        if (pos.getId() == null) {
            pos.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        } else {
            verifyPosExists(pos.getId());
        }
        pos.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return posDataService.upsert(pos);
    }

    private Pos verifyPosExists(@NonNull Long id) throws PosNotFoundException {
        return posDataService.getById(id)
                .orElseThrow(() -> new PosNotFoundException("POS with ID " + id + " does not exist."));
    }

    private Pos verifyPosExists(@NonNull String name) throws PosNotFoundException {
        return posDataService.getByName(name)
                .orElseThrow(() -> new PosNotFoundException("POS with name \"" + name + "\" does not exist."));
    }
}
