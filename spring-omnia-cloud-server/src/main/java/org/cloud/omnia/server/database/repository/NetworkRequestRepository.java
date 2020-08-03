package org.cloud.omnia.server.database.repository;

import org.cloud.omnia.server.database.entity.NetworkRequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkRequestRepository extends CrudRepository<NetworkRequestEntity, Long> {
}
