package com.greentech.backend.controller;

import com.greentech.backend.model.PanelStatus;
import com.greentech.backend.model.SolarPanel;
import com.greentech.backend.repository.SolarPanelRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDateTime;

@RestController //Le dice a Spring que esto es una API REST
@CrossOrigin(origins = "*", allowedHeaders = "*") // 🔥 Cambiado para permitir Render y cualquier origen global
@RequestMapping("/api/panels")//Define la ruta base para todas las operaciones relacionadas con paneles solares
public class SolarPanelController {

    private final SolarPanelRepository repository;

    // 4. Inyección de dependencia del repositorio a través del constructor
    public SolarPanelController(SolarPanelRepository repository) {
        this.repository = repository;
    }

    // HTTP GET: Devuelve todos los paneles a React
    @GetMapping
    public List<SolarPanel> getAllPanels() {
        return repository.findAll();
    }

    
   

    // HTTP POST: Recibe un nuevo panel de React, le asigna la hora actual y lo guarda
    @PostMapping
    public SolarPanel createPanel(@RequestBody SolarPanel newPanel) {
        newPanel.setInstallationDate(LocalDateTime.now()); // ✅ Corregido a camelCase
        newPanel.setLastUpdate(LocalDateTime.now());       // ✅ Corregido a camelCase
        return repository.save(newPanel);
    }


}