package cj.esanar;

import cj.esanar.persistence.entity.ERole;
import cj.esanar.persistence.entity.PermissionsEntity;
import cj.esanar.persistence.entity.RoleEntity;
import cj.esanar.persistence.entity.UserEntity;
import cj.esanar.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ESanarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESanarApplication.class, args);
    }
    /*@Bean
    CommandLineRunner unit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            PermissionsEntity createPermission = PermissionsEntity.builder().name("CREATE").build();
            PermissionsEntity readPermission = PermissionsEntity.builder().name("READ").build();
            PermissionsEntity deletePermission = PermissionsEntity.builder().name("DELETE").build();
            PermissionsEntity updatePermission = PermissionsEntity.builder().name("UPDATE").build();
            PermissionsEntity refactorePermission = PermissionsEntity.builder().name("REFACTOR").build();

            RoleEntity admin= RoleEntity.builder()
                    .roleEnum(ERole.ADMIN)
                    .listaPermisos(Set.of(createPermission, readPermission, deletePermission, updatePermission))
                    .build();
            RoleEntity enfRole= RoleEntity.builder()
                    .roleEnum(ERole.ENF)
                    .listaPermisos(Set.of(createPermission, readPermission, deletePermission, updatePermission))
                    .build();

            UserEntity userAdmin= UserEntity.builder()
                    .username("jeffer")
                    .password(passwordEncoder.encode("milluh123"))
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isEnabled(true)
                    .isCredentialsNonExpired(true)
                    .roles(Set.of(admin))
                    .build();
            UserEntity userEnf= UserEntity.builder()
                    .username("angelica")
                    .password(passwordEncoder.encode("camila123"))
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isEnabled(true)
                    .isCredentialsNonExpired(true)
                    .roles(Set.of(enfRole))
                    .build();

            userRepository.saveAll(List.of(userAdmin,userEnf));
        };
    }*/

}
