package cj.esanar.persistence.repository;

import cj.esanar.persistence.entity.PacienteEntity;
import org.springframework.data.repository.CrudRepository;


public interface PacienteRepository extends CrudRepository<PacienteEntity,Long> {

}
