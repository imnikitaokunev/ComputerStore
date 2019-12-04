package com.nikitkasss.store.repository;

import com.nikitkasss.store.dto.PositionDto;
import com.nikitkasss.store.model.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionRepository extends CrudRepository<Position, Long> {
    List<PositionDto> getByName(String name);
}
