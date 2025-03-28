package cj.esanar.persistence.repository;

import cj.esanar.persistence.entity.HistoriaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaRepository extends CrudRepository<HistoriaEntity, Long> {

}
