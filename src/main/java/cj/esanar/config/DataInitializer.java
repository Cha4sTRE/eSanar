package cj.esanar.config;

import cj.esanar.persistence.entity.*;
import cj.esanar.persistence.repository.PacienteRepository;
import cj.esanar.persistence.repository.UserRepository;
import cj.esanar.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final PacienteService pacienteService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        PermissionsEntity createPermission = PermissionsEntity.builder().name("CREATE").build();
        PermissionsEntity readPermission = PermissionsEntity.builder().name("READ").build();
        PermissionsEntity deletePermission = PermissionsEntity.builder().name("DELETE").build();
        PermissionsEntity updatePermission = PermissionsEntity.builder().name("UPDATE").build();
        PermissionsEntity refactorePermission = PermissionsEntity.builder().name("REFACTOR").build();

        RoleEntity admin= RoleEntity.builder()
                .name(ERole.ADMIN)
                .listaPermisos(Set.of(createPermission, readPermission, deletePermission, updatePermission))
                .build();
        RoleEntity enfRole= RoleEntity.builder()
                .name(ERole.ENF)
                .listaPermisos(Set.of(createPermission, readPermission, deletePermission, updatePermission))
                .build();

        UserEntity userAdmin= UserEntity.builder()
                .username("jeffer")
                .password(passwordEncoder.encode("milluh123"))
                .email("chaustrejefferson@gmail.com")
                .telefono(3166846822L)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .roles(Set.of(admin))
                .build();
        UserEntity userEnf= UserEntity.builder()
                .username("angelica")
                .password(passwordEncoder.encode("camila123"))
                .email("angelica.gaitan.duran@gmail.com")
                .telefono(3229433138L)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .roles(Set.of(enfRole))
                .build();



        PacienteEntity paciente1 = PacienteEntity.builder()
                .nombre("david")
                .apellido("aceros")
                .tipoDocumento("cedula de ciudadania")
                .identificacion(102356969L)
                .telefono(315568956L)
                .direccion("calle 13 #69 calle cucuta")
                .barrio("peru")
                .fechaNacimiento(LocalDate.parse("2016-03-15"))
                .sexo("masculino")
                .tipoSangre("O+")
                .correo("hierros@gmail.com")
                .ocupacion("Estudiaante")
                .entidad("EPS sanitas")
                .estadoCivil("soltero")
                .build();
        PacienteEntity paciente2 = PacienteEntity.builder()
                .nombre("juan")
                .apellido("marco")
                .tipoDocumento("cedula de ciudadania")
                .identificacion(102356969L)
                .telefono(315568956L)
                .direccion("calle 13 #69 calle cucuta")
                .barrio("peru")
                .fechaNacimiento(LocalDate.parse("2014-11-11"))
                .sexo("masculino")
                .tipoSangre("O+")
                .correo("hierros@gmail.com")
                .ocupacion("Estudiante")
                .entidad("EPS sanitas")
                .estadoCivil("soltero")
                .build();

        PacienteEntity paciente3 = PacienteEntity.builder()
                .nombre("Maria")
                .apellido("Gomez")
                .tipoDocumento("cedula de ciudadania")
                .identificacion(103456789L)
                .telefono(314567890L)
                .direccion("cra 45 #10-23")
                .barrio("centro")
                .fechaNacimiento(LocalDate.parse("1998-05-06"))
                .sexo("femenino")
                .tipoSangre("A+")
                .correo("maria.gomez@gmail.com")
                .ocupacion("Ingeniera")
                .entidad("EPS saludtotal")
                .estadoCivil("casada")
                .build();

        PacienteEntity paciente4 = PacienteEntity.builder()
                .nombre("Carlos")
                .apellido("Perez")
                .tipoDocumento("tarjeta de identidad")
                .identificacion(100987654L)
                .telefono(316789456L)
                .direccion("av siempre viva 742")
                .barrio("springfield")
                .fechaNacimiento(LocalDate.parse("2010-03-06"))
                .sexo("masculino")
                .tipoSangre("B-")
                .correo("carlitos.p@gmail.com")
                .ocupacion("Estudiante")
                .entidad("EPS compensar")
                .estadoCivil("soltero")
                .build();
        userRepository.saveAll(List.of(userAdmin,userEnf));
        pacienteService.guardaPaciente(paciente1);
        pacienteService.guardaPaciente(paciente2);
        pacienteService.guardaPaciente(paciente3);
        pacienteService.guardaPaciente(paciente4);
    }
}
