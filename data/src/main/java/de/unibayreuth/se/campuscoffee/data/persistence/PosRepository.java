package de.unibayreuth.se.campuscoffee.data.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for persisting point-of-sale (POS) entities.
 */
public interface PosRepository extends JpaRepository<PosEntity, Long> {
    Optional<PosEntity> findByName(String name);
}
