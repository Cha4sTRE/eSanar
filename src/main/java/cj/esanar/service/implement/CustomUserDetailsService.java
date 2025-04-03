package cj.esanar.service.implement;

import cj.esanar.persistence.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailsService implements UserDetails {

    private final UserEntity user;

    public CustomUserDetailsService(UserEntity userEntity){
        this.user=userEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name()))));
        user.getRoles().stream()
                .flatMap(role -> role.getListaPermisos().stream())
                .forEach(permissionsEntity -> authorities.add(new SimpleGrantedAuthority(permissionsEntity.getName())));
        return authorities;
    }
    public Long getId(){return user.getId();}
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
    public String getEmail(){
        return user.getEmail();
    }
    public Long getTelefono(){
        return user.getTelefono();
    }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
