package com.greentech.backend.repository;

import com.greentech.backend.model.SolarPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Le dice a Spring que este es un componente de acceso a datos
public interface SolarPanelRepository extends JpaRepository<SolarPanel, Long> {
// Heredando de JpaRepository ganamos automáticamente todos los métodos CRUD 
// (save, findAll, findById, delete, etc.)
}

