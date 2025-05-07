package cj.esanar.service.implement;

import cj.esanar.persistence.entity.HistoriaEntity;
import cj.esanar.persistence.repository.HistoriaRepository;
import cj.esanar.service.HistoriaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class HistoriaServiceImpl implements HistoriaService {

    private final HistoriaRepository historiaRepository;

    @Override
    public List<HistoriaEntity> listaHistorias() {
        return (List<HistoriaEntity>) historiaRepository.findAll();
    }


    @Override
    public void guardaHistoria(HistoriaEntity historia) {
        historiaRepository.save(historia);
    }
    @Override
    public void guardaHistorias(List<HistoriaEntity> historias){
        historiaRepository.saveAll(historias);
    }
    @Override
    public HistoriaEntity buscaHistoria(Long id) {
        return historiaRepository.findById(id).orElse(null);
    }

    @Override
    public void borraHistoria(HistoriaEntity historia) {
        historiaRepository.delete(historia);
    }
}
