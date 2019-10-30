package io.devfactory.license.repository;

import io.devfactory.license.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRedisRepository extends CrudRepository<Organization, String> {

}
