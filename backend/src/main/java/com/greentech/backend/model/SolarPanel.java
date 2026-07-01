package com.greentech.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "solar_panels")
public class SolarPanel {     

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(nullable = false, length = 100)
    private String model;

    @JsonProperty("currentOutput") // Lee esto desde Insomnia
    @Column(name = "current_output", nullable = false) // Guarda esto en Postgres
    private Double currentOutput;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PanelStatus status; 

    @JsonProperty("installationDate") // Lee esto desde Insomnia
    @Column(name = "installation_date", nullable = false) // Guarda esto en Postgres
    private LocalDateTime installationDate;

    @JsonProperty("lastUpdate") // Lee esto desde Insomnia
    @Column(name = "last_update", nullable = false) // Guarda esto en Postgres
    private LocalDateTime lastUpdate;
    
    // Constructor vacío obligado por JPA
    public SolarPanel() {}

    // Constructor completo
    public SolarPanel(String model, Double currentOutput, PanelStatus status, LocalDateTime installationDate, LocalDateTime lastUpdate) {
        this.model = model;
        this.currentOutput = currentOutput;
        this.status = status;
        this.installationDate = installationDate;
        this.lastUpdate = lastUpdate;
    }

    // Getters y Setters corregidos en CamelCase estándar
    public Long getId() {
        return id;  
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getCurrentOutput() {
        return currentOutput;
    }

    public void setCurrentOutput(Double currentOutput) {
        this.currentOutput = currentOutput;
    }

    public PanelStatus getStatus() {
        return status;
    }

    public void setStatus(PanelStatus status) {
        this.status = status;
    }

    public LocalDateTime getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(LocalDateTime installationDate) {
        this.installationDate = installationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}