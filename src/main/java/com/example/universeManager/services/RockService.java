package com.example.universeManager.services;

import com.example.universeManager.entity.MasterEntity;
import com.example.universeManager.entity.RockEntity;
import com.example.universeManager.exception.RockAlreadyExistException;
import com.example.universeManager.exception.RockAlreadyHaveMasterException;
import com.example.universeManager.exception.RockNotFoundException;
import com.example.universeManager.model.Rock;
import com.example.universeManager.repository.MasterRepo;
import com.example.universeManager.repository.RockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RockService {

    @Autowired
    private RockRepo rockRepo;
    @Autowired
    private MasterRepo masterRepo;

    public RockEntity addRock(RockEntity rock) throws RockAlreadyExistException {
        if (rockRepo.findByName(rock.getName()) != null) {
            throw new RockAlreadyExistException("Планета с таким названием уже есть! Назовите ее как-нибудь еще!");
        }
        return rockRepo.save(rock);
    }

    public String appointMasterToRock (Long rockId, Long masterId) throws RockAlreadyHaveMasterException {
        MasterEntity master = masterRepo.findById(masterId).get();
        RockEntity rock = rockRepo.findById(rockId).get();
        if (!rock.isHaveMaster()) {
            rock.setHaveMaster(true);
            master.setHaveRock(true);
            rock.setMaster(master);
            rockRepo.save(rock);
            masterRepo.save(master);
        } else {
            throw new RockAlreadyHaveMasterException("У планеты уже есть Повелитель");
        }
        return master.getName() + " назначен Повелителем планеты " + rock.getName();
    }

    public Rock getRock(Long id) throws RockNotFoundException {
        RockEntity rock;
        if (rockRepo.findById(id).isPresent()) {
            rock = rockRepo.findById(id).get();
        } else {
            throw new RockNotFoundException("Планета не найдена!");
        }
        return Rock.toModel(rock);
    }

    public ArrayList<Rock> findAllRocks() {
        List<RockEntity> list1 = (List<RockEntity>) rockRepo.findAll(Sort.by(Sort.Direction.ASC,"id"));
        ArrayList<Rock> list2 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list2.add(Rock.toModel(list1.get(i)));
        }
        return list2;
    }

    public String rockDestroyer(Long rockId) throws RockNotFoundException {
        RockEntity rock;
        MasterEntity master;
        if (rockRepo.findById(rockId).isPresent()) {
            rock = rockRepo.findById(rockId).get();
            master = rock.getMaster();
            master.setHaveRock(false);
            rockRepo.deleteById(rockId);
            masterRepo.save(master);
        } else {
            throw new RockNotFoundException("Такой планеты нет!");
        }
        return "Планета уничтожена!";
    }
}
