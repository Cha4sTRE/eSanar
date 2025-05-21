package cj.esanar.service;

import cj.esanar.persistence.entity.ConsultaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;


public interface ConsultaService {

   Set<ConsultaEntity> listaConsultas();

   Page<ConsultaEntity> listaConsultas(Pageable pageable, Long id);
   Page<ConsultaEntity> listaConsultas(Pageable pageable,Long id,String filtros);
   void guardarConsulta(ConsultaEntity consulta);
   void guardarConsultas(List<ConsultaEntity> consultas);
   ConsultaEntity consultaPorId(ConsultaEntity consulta);
   void eliminarConsulta(ConsultaEntity consulta);

}
