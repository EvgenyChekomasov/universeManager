package com.example.universeManager.services;

import com.example.universeManager.entity.MasterEntity;
import com.example.universeManager.exception.MasterAlreadyExistException;
import com.example.universeManager.exception.MasterNotFoundException;
import com.example.universeManager.model.Master;
import com.example.universeManager.repository.MasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterService {

    @Autowired
    private MasterRepo masterRepo;

    public MasterEntity addMaster(MasterEntity master) throws MasterAlreadyExistException {
        if (masterRepo.findByName(master.getName()) != null) {
            throw new MasterAlreadyExistException ("Повелитель с таким именем уже есть! Дайте ему новое имя!");
        }
        return masterRepo.save(master);

    }

    public Master findMaster(Long id) throws MasterNotFoundException {
        MasterEntity master;
        if (masterRepo.findById(id).isPresent()) {
            master = masterRepo.findById(id).get();
        } else {
            throw new MasterNotFoundException("Повелитель не найден!");
        }
        return Master.toModel(master);
    }

    public ArrayList<MasterEntity> findSlackers() {
        return (ArrayList<MasterEntity>) masterRepo.findAllByHaveRock(false);
    }

    public ArrayList<Master> findYoungMasters() {
        List<MasterEntity> list = (List<MasterEntity>) masterRepo.findAll(Sort.by(Sort.Direction.ASC,"age"));
        ArrayList<Master> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(Master.toModel(list.get(i)));
        }
        ArrayList<Master> list2 = new ArrayList<>();
        if (list1.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                list2.add(list1.get(i));
            }
            return list2;
        } else {
            return list1;
        }
    }

    public ArrayList<Master> findAllMasters() {
        List<MasterEntity> list1 = (List<MasterEntity>) masterRepo.findAll(Sort.by(Sort.Direction.ASC,"id"));
        ArrayList<Master> list2 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            list2.add(Master.toModel(list1.get(i)));
        }
        return list2;
    }
}
