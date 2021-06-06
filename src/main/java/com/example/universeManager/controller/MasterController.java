package com.example.universeManager.controller;

import com.example.universeManager.entity.MasterEntity;
import com.example.universeManager.exception.MasterAlreadyExistException;
import com.example.universeManager.exception.MasterNotFoundException;
import com.example.universeManager.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/masters")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping("/")
    public String masterPage () {
        return "masters";
    }

    @GetMapping("/new")
    public String newMaster(Model model) {
        model.addAttribute("master", new MasterEntity());
        return "newMaster";
    }

    @PostMapping
    public ResponseEntity addMaster(@ModelAttribute("master") MasterEntity master) {
        try {
            masterService.addMaster(master);
            return ResponseEntity.ok("Повелитель добавлен!");
        } catch (MasterAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping
    public ResponseEntity getOneMaster(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(masterService.findMaster(id));
        } catch (MasterNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping("/slackers")
    public ResponseEntity getAllSlackers () {
        try {
            return ResponseEntity.ok(masterService.findSlackers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllMasters() {
        try {
            return ResponseEntity.ok(masterService.findAllMasters());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping("/young")
    public ResponseEntity getYoungMasters() {
        try {
            return ResponseEntity.ok(masterService.findYoungMasters());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }
}
