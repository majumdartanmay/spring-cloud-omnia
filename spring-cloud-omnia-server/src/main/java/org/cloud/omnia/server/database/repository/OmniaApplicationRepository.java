package org.cloud.omnia.server.database.repository;

import org.cloud.omnia.server.database.entity.OmniaApplicationEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OmniaApplicationRepository extends CrudRepository<OmniaApplicationEntity, Long> {
    List<OmniaApplicationEntity> findAll(Specification<OmniaApplicationEntity> specification);
    List<OmniaApplicationEntity> findByCreatedOnGreaterThanEqual(Date createdOn);
}
