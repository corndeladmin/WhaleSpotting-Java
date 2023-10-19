package com.whalespottingjava.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${com.whalespottingjava.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI openApiConfig() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Dev Environment");

        Info info = new Info()
                .title("Whale Spotting API")
                .description("This API exposes endpoints for Whale Spotting");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
