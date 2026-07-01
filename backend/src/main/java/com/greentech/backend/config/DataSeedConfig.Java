package com.greentech.backend.config;

import com.greentech.backend.model.SolarPanel;
import com.greentech.backend.model.PanelStatus;
import com.greentech.backend.repository.SolarPanelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration // Le dice a Spring que este archivo contiene configuraciones del sistema
public class DataSeedConfig {

    @Bean // CommandLineRunner es una interfaz que ejecuta este código justo al arrancar la app
    CommandLineRunner initDatabase(SolarPanelRepository repository) {
        return args -> {
            // Verificamos si la base de datos está vacía para no duplicar datos
            if (repository.count() == 0) {
                System.out.println("🌱 Insertando paneles solares de prueba en la base de datos...");
                
                // Usamos el constructor de SolarPanel para registrar 3 paneles con distintos estados
                repository.save(new SolarPanel("Panel Zona Norte A1", 345.2, PanelStatus.OK, LocalDateTime.now()));
                repository.save(new SolarPanel("Panel Central B3", 120.0, PanelStatus.WARNING, LocalDateTime.now()));
                repository.save(new SolarPanel("Panel Techo Principal C2", 0.0, PanelStatus.FAULT, LocalDateTime.now()));
                
                System.out.println("✅ ¡Paneles de prueba insertados con éxito!");
            }
        };
    }
}