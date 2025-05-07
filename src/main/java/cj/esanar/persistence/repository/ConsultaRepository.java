package cj.esanar.persistence.repository;

import cj.esanar.persistence.entity.ConsultaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity,Long> {
    @Query("""
    SELECT c FROM ConsultaEntity c
    WHERE c.historiaClinica.id = :id
    """)
    Page<ConsultaEntity> findAllById(Pageable pageable,Long id);

    @Query("""
    SELECT c FROM ConsultaEntity c
    WHERE c.historiaClinica.id = :id
    AND (
        LOWER(c.motivoConsulta) LIKE LOWER(CONCAT('%', :filtro, '%'))
        OR LOWER(c.diagnosticoPrincipal) LIKE LOWER(CONCAT('%', :filtro, '%'))
        OR LOWER(c.enfermera.username) LIKE LOWER(CONCAT('%', :filtro, '%'))
    )
""")
    Page<ConsultaEntity> findAllById(Pageable pageable,Long id,@Param("filtro") String filtro);

}
