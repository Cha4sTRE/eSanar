package cj.esanar.service;

import cj.esanar.persistence.entity.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PacienteService {

    List<PacienteEntity> listaPacientes();

    Page<PacienteEntity> listaPacientes(Pageable pageable,String filtro);
    Page<PacienteEntity> listaPacientes(Pageable pageable);
    PacienteEntity findPacienteById(PacienteEntity paciente);
    void guardaPaciente(PacienteEntity paciente);
    void guardaPacientes(List<PacienteEntity> pacientes);
    void borraPaciente(PacienteEntity paciente);

}
