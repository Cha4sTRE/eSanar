package cj.esanar.service;

import cj.esanar.persistence.entity.PacienteEntity;
import cj.esanar.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    @Transactional
    public List<PacienteEntity> listaPacientes() {
        return (List<PacienteEntity>) pacienteRepository.findAll();
    }

    @Override
    @Transactional
    public PacienteEntity findPacienteById(PacienteEntity paciente) {
        return pacienteRepository.findById(paciente.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void guardaPaciente(PacienteEntity paciente) {
        System.out.println("Paciente ID: " + paciente.getId());
        pacienteRepository.save(paciente);
    }

    @Override
    @Transactional
    public void borraPaciente(PacienteEntity paciente) {
        pacienteRepository.delete(paciente);
    }
}
