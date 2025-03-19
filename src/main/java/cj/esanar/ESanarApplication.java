package cj.esanar;


import cj.esanar.persistence.entity.*;
import cj.esanar.persistence.repository.PacienteRepository;
import cj.esanar.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class ESanarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESanarApplication.class, args);
    }
    @Bean
    CommandLineRunner unit(UserRepository userRepository, PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        return args -> {
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
            userRepository.saveAll(List.of(userAdmin,userEnf));
            PacienteEntity paciente1 = PacienteEntity.builder()
                    .nombre("david")
                    .apellido("aceros")
                    .tipoDocumento("cedula de ciudadania")
                    .identificacion(102356969L)
                    .telefono(315568956L)
                    .direccion("calle 13 #69 calle cucuta")
                    .barrio("peru")
                    .fechaNacimiento("11/11/2001")
                    .sexo("masculino")
                    .tipoSangre("O+")
                    .correo("hierros@gmail.com")
                    .ocupacion("Estudiaante")
                    .entidad("EPS sanitas")
                    .estadoCivil("soltero")
                    .build();
            PacienteEntity paciente2 = PacienteEntity.builder()
                    .nombre("david")
                    .apellido("aceros")
                    .tipoDocumento("cedula de ciudadania")
                    .identificacion(102356969L)
                    .telefono(315568956L)
                    .direccion("calle 13 #69 calle cucuta")
                    .barrio("peru")
                    .fechaNacimiento("11/11/2001")
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
                    .fechaNacimiento("05/06/1998")
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
                    .fechaNacimiento("22/03/2010")
                    .sexo("masculino")
                    .tipoSangre("B-")
                    .correo("carlitos.p@gmail.com")
                    .ocupacion("Estudiante")
                    .entidad("EPS compensar")
                    .estadoCivil("soltero")
                    .build();

            pacienteRepository.save(paciente1);
            pacienteRepository.save(paciente2);
            pacienteRepository.save(paciente3);
            pacienteRepository.save(paciente4);
        };

    }
}
