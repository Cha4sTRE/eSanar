package cj.esanar.service.implement;

import cj.esanar.persistence.entity.ConsultaEntity;
import cj.esanar.persistence.repository.ConsultaRepository;
import cj.esanar.service.ConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Override
    public Set<ConsultaEntity> listaConsultas() {
        return (Set<ConsultaEntity>) consultaRepository.findAll();
    }

    @Override
    public void guardarConsulta(ConsultaEntity consulta) {
        consultaRepository.save(consulta);
    }
    @Override
    public void guardarConsultas(List<ConsultaEntity> consultas){
        consultaRepository.saveAll(consultas);
    }
    @Override
    public ConsultaEntity consultaPorId(ConsultaEntity consulta) {
        return consultaRepository.findById(consulta.getId()).orElse(null);
    }

    @Override
    public void eliminarConsulta(ConsultaEntity consulta) {
        consultaRepository.delete(consulta);
    }
}
