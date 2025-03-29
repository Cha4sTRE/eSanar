package cj.esanar.controller;

import cj.esanar.persistence.entity.ConsultaEntity;
import cj.esanar.persistence.entity.HistoriaEntity;
import cj.esanar.service.ConsultaService;
import cj.esanar.service.HistoriaService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor

@Controller
@RequestMapping("/consulta")
@PreAuthorize("hasRole('ENF')")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final HistoriaService historiaService;

    @GetMapping("/historias")
    public String historias(Model model) {

        DateTimeFormatter formato= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<HistoriaEntity> historias= historiaService.listaHistorias();

        model.addAttribute("formato", formato);
        model.addAttribute("historias", historias);
        return "consulta/historias";
    }

    @GetMapping("/nueva")
    public String nueva(ConsultaEntity consulta, Model model) {

        LocalDateTime ahora= LocalDateTime.now();
        DateTimeFormatter horaFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        model.addAttribute("consulta", new ConsultaEntity());
        model.addAttribute("hora", ahora.format(horaFormat));
        return "consulta/consulta-form";
    }

    @PostMapping("/agregar")
    public String agregar(ConsultaEntity consulta,@RequestParam LocalDateTime fechaHora,Errors errors) {
        if(errors.hasErrors()) {
            return "consulta/consulta-form";
        }
        consultaService.guardarConsulta(consulta);
        return "redirect:/consulta";
    }

}
