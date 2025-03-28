package cj.esanar.service;

import cj.esanar.persistence.entity.ConsultaEntity;

import java.util.List;


public interface ConsultaService {

   List<ConsultaEntity> listaConsultas();
   void guardarConsulta(ConsultaEntity consulta);
   ConsultaEntity consultaPorId(ConsultaEntity consulta);
   void eliminarConsulta(ConsultaEntity consulta);

}
