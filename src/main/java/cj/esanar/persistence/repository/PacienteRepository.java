package cj.esanar.persistence.repository;

import cj.esanar.persistence.entity.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface PacienteRepository extends JpaRepository<PacienteEntity,Long> {
    @Query("SELECT p from PacienteEntity p WHERE CONCAT(p.id,p.nombre,p.apellido,p.correo,p.telefono,p.identificacion) LIKE %?1%")
    Page<PacienteEntity> findByfilter(Pageable page,String filtro);

}
