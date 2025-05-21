package cj.esanar.persistence.repository;

import cj.esanar.persistence.entity.PermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionsRepository extends JpaRepository<PermissionsEntity, Long> {
    Optional<PermissionsEntity> findByName(String name);
}
