package cj.esanar.service;

import cj.esanar.persistence.entity.ConsultaEntity;

import java.util.List;
import java.util.Set;


public interface ConsultaService {

   Set<ConsultaEntity> listaConsultas();
   void guardarConsulta(ConsultaEntity consulta);
   void guardarConsultas(List<ConsultaEntity> consultas);
   ConsultaEntity consultaPorId(ConsultaEntity consulta);
   void eliminarConsulta(ConsultaEntity consulta);

}
