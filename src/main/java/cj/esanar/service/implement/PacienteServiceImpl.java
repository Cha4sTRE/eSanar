package cj.esanar.service.implement;

import cj.esanar.persistence.entity.PacienteEntity;
import cj.esanar.persistence.repository.PacienteRepository;
import cj.esanar.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Page<PacienteEntity> listaPacientes(Pageable page,String filtro) {
        if(filtro!=null||filtro==""){
            return pacienteRepository.findByfilter(page, filtro);
        }
        return listaPacientes(page);
    }

    @Override
    public Page<PacienteEntity> listaPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    @Override
    public List<PacienteEntity> listaPacientes() {
        return pacienteRepository.findAll();
    }

    @Override
    public PacienteEntity findPacienteById(PacienteEntity paciente) {
        return pacienteRepository.findById(paciente.getId()).orElse(null);
    }

    @Override
    public void guardaPaciente(PacienteEntity paciente) {
        paciente.setEdad(calculateEdad(paciente.getFechaNacimiento()));
        pacienteRepository.save(paciente);
    }
    public void guardaPacientes(List<PacienteEntity> pacientes) {
        pacienteRepository.saveAll(pacientes);
    }
    @Override
    public void borraPaciente(PacienteEntity paciente) {
        pacienteRepository.delete(paciente);
    }

    private int calculateEdad(LocalDate fechaNacimiento){

        LocalDate today = LocalDate.now();
        Period periodo= Period.between(fechaNacimiento,today);
        int years = periodo.getYears();
        return years;
    }
}
