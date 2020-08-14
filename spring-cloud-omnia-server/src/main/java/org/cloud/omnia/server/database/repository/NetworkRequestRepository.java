package org.cloud.omnia.server.database.repository;

import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkRequestRepository extends CrudRepository<NetworkRequestEntity, Long> {
    List<NetworkRequestEntity> findAll(Specification<NetworkRequestEntity> specification);
}
