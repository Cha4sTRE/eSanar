package cj.esanar.contrlller;


import cj.esanar.persistence.entity.PacienteEntity;
import cj.esanar.service.PacienteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enf")
@PreAuthorize("hasRole('ENF')")
public class EnfController {

    @Autowired
    private PacienteServiceImpl pacienteServiceImpl;

    @GetMapping("/")
    public String home(Model model) {

        List<PacienteEntity> pacientes= pacienteServiceImpl.listaPacientes();
        model.addAttribute("pacientes", pacientes);
        return "enf/home";
    }

    @GetMapping("paciente/nuevo")
    public String nuevo(PacienteEntity paciente,Model model) {
        model.addAttribute("paciente", new PacienteEntity());
        return "enf/paciente-form";
    }

    @PostMapping("paciente/guardar")
    public String guardar(@Valid PacienteEntity paciente, Errors errors) {
        if (errors.hasErrors()) {
            return "enf/paciente-form";
        }
        pacienteServiceImpl.guardaPaciente(paciente);
        return "redirect:/enf/";
    }

    @GetMapping("paciente/{nombre}")
    public String paciente(PacienteEntity paciente, Model model) {

        PacienteEntity pacienteEspecifico = pacienteServiceImpl.findPacienteById(paciente);
        model.addAttribute("paciente", pacienteEspecifico);
        model.addAttribute("id",paciente.getId());
        return "enf/paciente-form";
    }

    @GetMapping("paciente/eliminar")
    public String eliminar(PacienteEntity paciente) {
        pacienteServiceImpl.borraPaciente(paciente);
        return "redirect:/enf/";
    }



}
