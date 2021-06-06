package com.example.universeManager.controller;

import com.example.universeManager.entity.RockEntity;
import com.example.universeManager.exception.RockAlreadyExistException;
import com.example.universeManager.exception.RockAlreadyHaveMasterException;
import com.example.universeManager.exception.RockNotFoundException;
import com.example.universeManager.services.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rocks")
public class RockController {

    @Autowired
    private RockService rockService;

    @GetMapping("/")
    public String rockPage () {
        return "rocks";
    }

    @GetMapping("/new")
    public String newRock(Model model) {
        model.addAttribute("rock", new RockEntity());
        return "newRock";
    }

    @PostMapping
    public ResponseEntity addRock(@ModelAttribute("rock") RockEntity rock) {
        try {
            rockService.addRock(rock);
            return ResponseEntity.ok("Планета добавлена!");
        } catch (RockAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping
    public ResponseEntity findRock(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(rockService.getRock(id));
        } catch (RockNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllRocks() {
        try {
            return ResponseEntity.ok(rockService.findAllRocks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @PutMapping
    public ResponseEntity appointMasterToRock (@RequestParam Long rockId,
                                                Long masterId) {
        try {
            return ResponseEntity.ok(rockService.appointMasterToRock(rockId, masterId));
        } catch (RockAlreadyHaveMasterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @DeleteMapping
    public ResponseEntity rockDestroyer (@RequestParam Long id) {
        try {
            return ResponseEntity.ok(rockService.rockDestroyer(id));
        } catch (RockNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }
}
