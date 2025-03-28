package cj.esanar.controller;

import cj.esanar.persistence.entity.ConsultaEntity;
import cj.esanar.service.ConsultaService;
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

@AllArgsConstructor

@Controller
@RequestMapping("/consulta")
@PreAuthorize("hasRole('ENF')")
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping("/historias")
    public String historias(Model model) {
        return "vista de historias";
    }

    @GetMapping("/nueva")
    public String nueva(ConsultaEntity consulta, Model model) {
        model.addAttribute("consulta", consulta);
        LocalDateTime ahora= LocalDateTime.now();
        DateTimeFormatter horaFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
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
