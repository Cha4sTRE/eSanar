package cj.esanar.config;

import cj.esanar.persistence.entity.*;
import cj.esanar.persistence.repository.RoleRepository;
import cj.esanar.persistence.repository.UserRepository;
import cj.esanar.service.ConsultaService;
import cj.esanar.service.HistoriaService;
import cj.esanar.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PacienteService pacienteService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final HistoriaService historiaService;
    private final ConsultaService consultaService;

    @Override
    public void run(String... args) throws Exception {
        PermissionsEntity createPermission = PermissionsEntity.builder().name("CREATE").build();
        PermissionsEntity readPermission = PermissionsEntity.builder().name("READ").build();
        PermissionsEntity deletePermission = PermissionsEntity.builder().name("DELETE").build();
        PermissionsEntity updatePermission = PermissionsEntity.builder().name("UPDATE").build();

        RoleEntity admin= RoleEntity.builder()
                .name(ERole.ADMIN)
                .listaPermisos(Set.of(createPermission, readPermission, deletePermission, updatePermission))
                .build();
        RoleEntity enfRole= RoleEntity.builder()
                .name(ERole.ENF)
                .listaPermisos(Set.of(createPermission, readPermission))
                .build();
        RoleEntity noPermiss= RoleEntity.builder()
                .name(ERole.VISITOR)
                .listaPermisos(Set.of(readPermission))
                .build();
        RoleEntity medicRole= RoleEntity.builder()
                .name(ERole.MEDIC)
                .listaPermisos(Set.of(createPermission, readPermission, updatePermission))
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
                .roles(Set.of(admin,enfRole,noPermiss,medicRole))
                .build();
        UserEntity userMedic= UserEntity.builder()
                .username("angelica")
                .password(passwordEncoder.encode("camila123"))
                .email("angelica.gaitan.duran@gmail.com")
                .telefono(3229433138L)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .roles(Set.of(medicRole))
                .build();
        UserEntity visitante= UserEntity.builder()
                .username("John Doe")
                .password(passwordEncoder.encode("john123"))
                .email("john@gmail.com")
                .telefono(3229433138L)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .roles(Set.of(noPermiss))
                .build();
        UserEntity userEnf= UserEntity.builder()
                .username("Wilson")
                .password(passwordEncoder.encode("wilson123"))
                .email("wilson@gmail.com")
                .telefono(3229433138L)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .roles(Set.of(enfRole))
                .build();
        userRepository.save(userAdmin);
        userAdmin.setRoles(Set.of(admin));
        userRepository.save(userAdmin);

        //userRepository.saveAll(Set.of(userAdmin,userMedic,visitante,userEnf));
        /*PacienteEntity paciente1 = PacienteEntity.builder()
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
        pacienteService.guardaPacientes(List.of(paciente1,paciente2, paciente3, paciente4));

        HistoriaEntity historia1= HistoriaEntity.builder()
                .paciente(paciente1).
                fechaCreacion(LocalDate.now()).
                build();
        HistoriaEntity historia2= HistoriaEntity.builder()
                .paciente(paciente2).
                fechaCreacion(LocalDate.now()).
                build();
        HistoriaEntity historia3= HistoriaEntity.builder()
                .paciente(paciente3).
                fechaCreacion(LocalDate.now()).
                build();
        HistoriaEntity historia4= HistoriaEntity.builder()
                .paciente(paciente4).
                fechaCreacion(LocalDate.now()).
                build();
        historiaService.guardaHistorias(List.of(historia1,historia2,historia3,historia4));
        ConsultaEntity consulta1= ConsultaEntity.builder()
                .enfermera(userEnf2)
                .historiaClinica(historia1)
                .diagnosticoPrincipal("Fiebre")
                .motivoConsulta("Dolor de cabeza y fiebre alta")
                .largo(10)
                .ancho(5)
                .profundidad("Superficial")
                .forma("Ovalada")
                .olor("No")
                .bordesHerida("Definidos")
                .infeccion("No")
                .exudadoTipo("Seroso")
                .exudadoNivel("Moderado")
                .fechaHoraAtencion(LocalDateTime.now())
                .horaFinal(LocalTime.of(11, 0))
                .build();
        ConsultaEntity consulta2= ConsultaEntity.builder()
                .enfermera(userEnf1)
                .historiaClinica(historia2)
                .diagnosticoPrincipal("anemia")
                .motivoConsulta("Dolor de cabeza y fiebre alta")
                .largo(10)
                .ancho(5)
                .profundidad("Superficial")
                .forma("Ovalada")
                .olor("No")
                .bordesHerida("Definidos")
                .infeccion("No")
                .exudadoTipo("Seroso")
                .exudadoNivel("Moderado")
                .fechaHoraAtencion(LocalDateTime.now())
                .horaFinal(LocalTime.of(11, 0))
                .build();
        ConsultaEntity consulta3= ConsultaEntity.builder()
                .enfermera(userEnf1)
                .historiaClinica(historia3)
                .diagnosticoPrincipal("resfriado")
                .motivoConsulta("Dolor de cabeza y fiebre alta")
                .largo(10)
                .ancho(5)
                .profundidad("Superficial")
                .forma("Ovalada")
                .olor("No")
                .bordesHerida("Definidos")
                .infeccion("No")
                .exudadoTipo("Seroso")
                .exudadoNivel("Moderado")
                .fechaHoraAtencion(LocalDateTime.now())
                .horaFinal(LocalTime.of(11, 0))
                .build();
        ConsultaEntity consulta4= ConsultaEntity.builder()
                .enfermera(userEnf1)
                .historiaClinica(historia4)
                .diagnosticoPrincipal("tuberculosis")
                .motivoConsulta("Dolor de cabeza y fiebre alta")
                .largo(10)
                .ancho(5)
                .profundidad("Superficial")
                .forma("Ovalada")
                .olor("No")
                .bordesHerida("Definidos")
                .infeccion("No")
                .exudadoTipo("Seroso")
                .exudadoNivel("Moderado")
                .fechaHoraAtencion(LocalDateTime.now())
                .horaFinal(LocalTime.of(11, 0))
                .build();


        historia1.agregarConsultas(consulta1);
        historia2.agregarConsultas(consulta2);
        historia3.agregarConsultas(consulta3);
        historia4.agregarConsultas(consulta4);
        consultaService.guardarConsultas(List.of(consulta1,consulta2,consulta3,consulta4));*/


    }
}
