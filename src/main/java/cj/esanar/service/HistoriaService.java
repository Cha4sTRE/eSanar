package cj.esanar.service;

import cj.esanar.persistence.entity.HistoriaEntity;

import java.util.List;

public interface HistoriaService {

    List<HistoriaEntity> listaHistorias();
    void guardaHistoria(HistoriaEntity historia);
    void guardaHistorias(List<HistoriaEntity> historias);
    HistoriaEntity buscaHistoria(Long id);
    void borraHistoria(HistoriaEntity historia);

}
