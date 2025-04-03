package cj.esanar.controller;

import cj.esanar.persistence.entity.ConsultaEntity;
import cj.esanar.persistence.entity.HistoriaEntity;
import cj.esanar.persistence.entity.UserEntity;
import cj.esanar.persistence.repository.UserRepository;
import cj.esanar.service.ConsultaService;
import cj.esanar.service.HistoriaService;
import cj.esanar.service.implement.CustomUserDetailsService;
import cj.esanar.service.implement.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor

@Controller
@RequestMapping("/consulta")
@PreAuthorize("hasRole('ENF')")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final HistoriaService historiaService;
    private final UserDetailServiceImpl userDetailService;


    @GetMapping("/historia/{nombre}")
    public String historias(Model model, HistoriaEntity historia, @PathVariable String nombre) {

        DateTimeFormatter formato= DateTimeFormatter.ofPattern("yyyy-MM-dd");

        model.addAttribute("consultas", historiaService.buscaHistoria(historia));
        model.addAttribute("pacienteNombre", nombre);
        model.addAttribute("formatoHora", formato);
        return "consulta/historias";
    }

    @GetMapping("/nueva")
    public String nueva(ConsultaEntity consulta,@RequestParam HistoriaEntity historiaId, Model model) {


        LocalDateTime ahora= LocalDateTime.now();
        DateTimeFormatter horaFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

        model.addAttribute("consulta", consulta);
        model.addAttribute("historiaPaciente",historiaService.buscaHistoria(historiaId));
        model.addAttribute("hora", ahora.format(horaFormat));
        return "consulta/consulta-form";
    }

    @PostMapping("/agregar")
    public String agregar(ConsultaEntity consulta, @RequestParam HistoriaEntity historiaPaciente, @RequestParam LocalDateTime fechaHora, Errors errors) {
        if(errors.hasErrors()) {
            return "consulta/consulta-form";
        }
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService enfUser= (CustomUserDetailsService) auth.getPrincipal();
        UserEntity enf= userDetailService.finById(enfUser.getId());

        HistoriaEntity hPaciente=historiaService.buscaHistoria(historiaPaciente);
        consulta.setEnfermera(enf);
        consulta.setFechaHoraAtencion(fechaHora);
        consulta.setHoraFinal(LocalTime.now());
        consulta.setHistoriaClinica(hPaciente);
        hPaciente.agregarConsultas(consulta);
        consultaService.guardarConsulta(consulta);
        return "redirect:/consulta/historia/"+hPaciente.getPaciente().getNombre()+"?"+"id="+hPaciente.getId();
    }

}
