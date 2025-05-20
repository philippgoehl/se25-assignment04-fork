package de.unibayreuth.se.campuscoffee.data.impl;

import de.unibayreuth.se.campuscoffee.data.mapper.PosEntityMapper;
import de.unibayreuth.se.campuscoffee.data.persistence.AddressEntity;
import de.unibayreuth.se.campuscoffee.data.persistence.PosEntity;
import de.unibayreuth.se.campuscoffee.data.persistence.PosRepository;
import de.unibayreuth.se.campuscoffee.domain.Pos;
import de.unibayreuth.se.campuscoffee.domain.exceptions.PosNotFoundException;
import de.unibayreuth.se.campuscoffee.domain.ports.PosDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the POS data service that the domain layer provides as a port.
 */
@Service
@RequiredArgsConstructor
class PosDataServiceImpl implements PosDataService {
    private final PosRepository repository;
    private final PosEntityMapper mapper;

    @Override
    public void clear() {
        repository.deleteAllInBatch();
        repository.flush();
    }

    @Override
    @NonNull
    public List<Pos> getAll() {
        return repository.findAll().stream()
                .map(mapper::fromEntity)
                .toList();
    }

    @Override
    @NonNull
    public Optional<Pos> getById(@NonNull Long id) throws PosNotFoundException {
        return repository.findById(id)
                .map(mapper::fromEntity);
    }

    @Override
    @NonNull
    public Optional<Pos> getByName(@NonNull String name) throws PosNotFoundException {
        return repository.findByName(name)
                .map(mapper::fromEntity);
    }

    @Override
    @NonNull
    public Pos upsert(@NonNull Pos pos) throws PosNotFoundException {
        if (pos.getId() == null) {
            // create new POS
            return mapper.fromEntity(repository.saveAndFlush(mapper.toEntity(pos)));
        }

        // update existing task
        PosEntity posEntity = repository.findById(pos.getId())
                .orElseThrow(() -> new PosNotFoundException("POS with ID " + pos.getId() + " does not exist."));
        // use mapper to be able to use the house number conversion logic
        PosEntity mappedPosEntity = mapper.toEntity(pos);
        // update fields
        posEntity.setName(mappedPosEntity.getName());
        posEntity.setDescription(mappedPosEntity.getDescription());
        posEntity.setType(mappedPosEntity.getType());
        posEntity.setCampus(mappedPosEntity.getCampus());
        AddressEntity addressEntity = posEntity.getAddress();
        addressEntity.setStreet(mappedPosEntity.getAddress().getStreet());
        addressEntity.setHouseNumber(mappedPosEntity.getAddress().getHouseNumber());
        addressEntity.setHouseNumberSuffix(mappedPosEntity.getAddress().getHouseNumberSuffix());
        addressEntity.setPostalCode(mappedPosEntity.getAddress().getPostalCode());
        addressEntity.setCity(mappedPosEntity.getAddress().getCity());
        posEntity.setUpdatedAt(LocalDateTime.now());

        return mapper.fromEntity(repository.saveAndFlush(posEntity));

    }
}
