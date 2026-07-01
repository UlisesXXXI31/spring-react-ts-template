package com.greentech.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server renderServer = new Server();
        // 👇 REEMPLAZA ESTA URL POR LA TUVA REAL DE RENDER
        renderServer.setUrl("https://greentech-backend-8rmi.onrender.com");
        renderServer.setDescription("Servidor de Producción en Render");

        return new OpenAPI().servers(List.of(renderServer));
    }
}