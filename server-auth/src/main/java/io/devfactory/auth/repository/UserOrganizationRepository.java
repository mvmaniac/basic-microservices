package io.devfactory.auth.repository;

import io.devfactory.auth.model.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOrganizationRepository extends JpaRepository<UserOrganization, String> {

    Optional<UserOrganization> findByUserName(String userName);

}
