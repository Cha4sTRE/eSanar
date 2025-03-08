package cj.esanar.service;

import cj.esanar.persistence.entity.UserEntity;
import cj.esanar.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("el usuario "+username+" no existe"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role->authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getListaPermisos().stream())
                .forEach(permissionsEntity -> authorities.add(new SimpleGrantedAuthority(permissionsEntity.getName())));

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isAccountNonLocked(),
                authorities);
    }

}
