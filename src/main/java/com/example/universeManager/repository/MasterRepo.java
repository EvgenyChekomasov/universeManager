package com.example.universeManager.repository;

import com.example.universeManager.entity.MasterEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MasterRepo extends PagingAndSortingRepository<MasterEntity, Long> {
    MasterEntity findByName (String name);
    Iterable<MasterEntity> findAllByHaveRock (boolean haveRock);
}
