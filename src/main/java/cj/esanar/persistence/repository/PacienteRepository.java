package cj.esanar.persistence.repository;

import cj.esanar.persistence.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PacienteRepository extends JpaRepository<PacienteEntity,Long> {

}
