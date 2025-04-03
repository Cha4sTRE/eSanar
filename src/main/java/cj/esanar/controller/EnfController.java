package cj.esanar.controller;


import cj.esanar.persistence.entity.HistoriaEntity;
import cj.esanar.persistence.entity.PacienteEntity;
import cj.esanar.service.HistoriaService;
import cj.esanar.service.PacienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@Controller
@RequestMapping("/enf")
@PreAuthorize("hasRole('ENF')")
public class EnfController {

    private final PacienteService pacienteServiceImpl;
    private final HistoriaService historiaServiceImpl;

    @GetMapping("/")
    public String home(Model model) {

        List<PacienteEntity> pacientes= pacienteServiceImpl.listaPacientes();
        List<HistoriaEntity> historias= historiaServiceImpl.listaHistorias();
        model.addAttribute("pacientes", pacientes);
        return "enf/home";
    }

    @GetMapping("paciente/nuevo")
    public String nuevo(PacienteEntity paciente,Model model) {
        model.addAttribute("paciente", new PacienteEntity());
        return "enf/paciente-form";
    }

    @PostMapping("paciente/guardar")
    public String guardar(@Valid PacienteEntity paciente,@RequestParam String fechaNacimiento, Errors errors) {
        if (errors.hasErrors()) {
            return "enf/paciente-form";
        }
        LocalDate dateNacimiento= LocalDate.parse(fechaNacimiento);
        paciente.setFechaNacimiento(dateNacimiento);
        paciente.setHistoriaEntity(new HistoriaEntity(null, dateNacimiento, paciente, Collections.emptySet()));
        pacienteServiceImpl.guardaPaciente(paciente);
        return "redirect:/enf/";
    }

    @GetMapping("paciente/{nombre}")
    public String paciente(PacienteEntity paciente, Model model, @PathVariable String nombre) {

        PacienteEntity pacienteEspecifico = pacienteServiceImpl.findPacienteById(paciente);

        DateTimeFormatter formato=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fecha= pacienteEspecifico.getFechaNacimiento().format(formato);

        model.addAttribute("fecha", fecha);
        model.addAttribute("nombre",nombre);
        model.addAttribute("paciente", pacienteEspecifico);
        return "enf/paciente-form";
    }

    @GetMapping("paciente/eliminar")
    public String eliminar(PacienteEntity paciente) {
        pacienteServiceImpl.borraPaciente(paciente);
        return "redirect:/enf/";
    }



}
