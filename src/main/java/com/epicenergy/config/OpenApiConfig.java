package com.epicenergy.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@SecurityScheme(
	name="bearerAuth",
	type=SecuritySchemeType.HTTP,
	bearerFormat="JWT",
	scheme="bearer"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Catalogo Libri",
                description = "" +
                        "Catalogo Online Libreria",
                contact = @Contact(
                        name = "Enrico Nepa",
                        url = "https://github.com/EnricoNepa",
                        email = "enrico9924@gmail.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/EnricoNepa/BE-ProgettoSettimana11/blob/main/LICENSE")),
        servers = @Server(url = "http://localhost:8080"))
public class OpenApiConfig {

}