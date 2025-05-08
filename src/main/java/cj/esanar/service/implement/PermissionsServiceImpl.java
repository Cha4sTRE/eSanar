package cj.esanar.service.implement;

import cj.esanar.persistence.entity.PermissionsEntity;
import cj.esanar.persistence.repository.PermissionsRepository;
import cj.esanar.service.PermissionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@AllArgsConstructor
public class PermissionsServiceImpl implements PermissionsService {

    private final PermissionsRepository permissionsRepository;

    @Override
    public void savePermissions(PermissionsEntity permissionsEntity) {
        permissionsRepository.save(permissionsEntity);
    }

    @Override
    public void updatePermissions(PermissionsEntity permissionsEntity) {
        permissionsRepository.save(permissionsEntity);
    }

    @Override
    public PermissionsEntity getByName(String name) {
        return permissionsRepository.findByName(name).orElse(null);
    }


}
