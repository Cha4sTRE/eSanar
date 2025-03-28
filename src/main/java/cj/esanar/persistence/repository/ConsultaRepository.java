package cj.esanar.persistence.repository;

import cj.esanar.persistence.entity.ConsultaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends CrudRepository<ConsultaEntity,Long> {

}
