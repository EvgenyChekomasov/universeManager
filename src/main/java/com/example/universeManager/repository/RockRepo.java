package com.example.universeManager.repository;

import com.example.universeManager.entity.RockEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RockRepo extends PagingAndSortingRepository<RockEntity, Long> {
    RockEntity findByName (String name);
}
