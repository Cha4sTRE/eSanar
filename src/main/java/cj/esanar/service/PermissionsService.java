package cj.esanar.service;

import cj.esanar.persistence.entity.PermissionsEntity;

import java.security.Permissions;
import java.util.Set;

public interface PermissionsService {

    void savePermissions(PermissionsEntity permissionsEntity);
    void updatePermissions(PermissionsEntity permissionsEntity);
    PermissionsEntity getByName(String name);


}
