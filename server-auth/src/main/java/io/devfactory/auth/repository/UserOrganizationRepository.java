package io.devfactory.auth.repository;

import io.devfactory.auth.model.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrganizationRepository extends JpaRepository<UserOrganization, String> {

    UserOrganization findByUserName(String userName);

}
