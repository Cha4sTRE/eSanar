package cj.esanar.service;

import cj.esanar.persistence.entity.PacienteEntity;

import java.util.List;

public interface PacienteService {

    List<PacienteEntity> listaPacientes();
    PacienteEntity findPacienteById(PacienteEntity paciente);
    void guardaPaciente(PacienteEntity paciente);
    void guardaPacientes(List<PacienteEntity> pacientes);
    void borraPaciente(PacienteEntity paciente);

}
