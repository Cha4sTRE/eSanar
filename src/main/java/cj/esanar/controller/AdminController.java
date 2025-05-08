package cj.esanar.controller;


import cj.esanar.persistence.entity.PermissionsEntity;
import cj.esanar.persistence.entity.RoleEntity;
import cj.esanar.persistence.entity.UserEntity;
import cj.esanar.persistence.repository.PermissionsRepository;
import cj.esanar.persistence.repository.RoleRepository;
import cj.esanar.service.implement.CustomUserDetailsService;
import cj.esanar.service.implement.UserDetailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AdminController {


    private UserDetailServiceImpl userDetailService;
    private RoleRepository roleRepository;
    private PermissionsRepository permissionsRepository;

    @GetMapping("/")
    public String dashboar(Model model) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetailsService usuarioAuth= (CustomUserDetailsService) auth.getPrincipal();
        List<UserEntity> users= userDetailService.getAllUsers();
        model.addAttribute("usuarioAuth", usuarioAuth);
        model.addAttribute("users", users);
        model.addAttribute("listaRoles", roleRepository.findAll());

        return "admin/dashboard";
    }
    @ModelAttribute("usuario")
    public UserEntity usuario() {
        return new UserEntity();
    }

    @GetMapping("/nuevoRegistro")
    public String nuevoRegistro(Model model) {
        model.addAttribute("listaRoles", roleRepository.findAll());
        return "admin/registro";
    }

    @GetMapping("/eliminarRegistro")
    public String eliminarRegistro(@RequestParam("id") Long id) {
        Optional<UserEntity> userOptional = Optional.ofNullable(userDetailService.getById(id));
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Si necesitas eliminar permisos específicos, hazlo aquí, por ejemplo:
            for (RoleEntity rol : user.getRoles()) {
                rol.setListaPermisos(null); // o permisos.clear() si ya están cargados
            }

            userDetailService.deleteUser(user);
        }

        return "redirect:/admin/";
    }



    @PostMapping("/guardarRegistro")
    public String guardarRegistro(@Valid @ModelAttribute("usuario") UserEntity usuario,
                                  @RequestParam(name = "permisos",required = false) Set<String> permisosSeleccionados, Errors errors){
        if(errors.hasErrors()){
            System.out.println("Errores en la validación: " + errors.getAllErrors());
            return "admin/registro";
        }

        usuario.setEnabled(true);
        usuario.setAccountNonLocked(true);
        usuario.setAccountNonExpired(true);
        usuario.setCredentialsNonExpired(true);

        Set<PermissionsEntity> permisos = new HashSet<>();
        if (permisosSeleccionados != null) {
            for (String nombrePermiso : permisosSeleccionados) {
                permissionsRepository.findByName(nombrePermiso).ifPresent(permisos::add);
            }
        }

        for (RoleEntity role : usuario.getRoles()) {
            role.setListaPermisos(permisos);
        }

        userDetailService.saveUser(usuario);
        return "redirect:/admin/";
    }

    @PostMapping("/isEnable")
    @ResponseBody
    public String isEnable(@RequestParam("id") Long id, @RequestParam("enabled") boolean enabled, HttpServletRequest request) {
        UserEntity user = userDetailService.getById(id);
        System.out.println("el id es: " + id);
        if (user != null) {
            user.setEnabled(enabled);
            userDetailService.saveUser(user);
            return "Usuario actualizado con éxito.";
        }


        return "Error: usuario no encontrado.";
    }




}
