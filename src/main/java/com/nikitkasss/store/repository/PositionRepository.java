package com.nikitkasss.store.repository;

import com.nikitkasss.store.dto.position.PositionInfoDto;
import com.nikitkasss.store.model.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionRepository extends CrudRepository<Position, Long> {
    List<PositionInfoDto> getByName(String name);
}
