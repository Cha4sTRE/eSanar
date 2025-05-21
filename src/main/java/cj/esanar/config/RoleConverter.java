package cj.esanar.config;

import cj.esanar.persistence.entity.ERole;
import cj.esanar.persistence.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements Converter<String, ERole> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ERole convert(String source) {
        try {
            return ERole.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rol no válido: " + source);
        }
    }

}
