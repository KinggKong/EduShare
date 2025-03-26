package com.example.profileservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@OpenAPIDefinition(
        tags = {
                @Tag(name = "01. Profile", description = "Profile of user APIs")
                // @Tag(name = "02. Accounts", description = "Account Management APIs"),
                // @Tag(name = "03. Roles", description = "Role Management APIs")
        }
)
public class SwaggerConfig {
    private static final String SCHEME_NAME = "Token";
    private static final String SCHEME = "Bearer";

    //    @Value("${spring.api.doc.vnp.url}")
    //    String url;

    @Bean
    public OpenAPI OpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8081");
        devServer.setDescription("Server URL in Local environment");

        //        Server stagingServer = new Server();
        //        stagingServer.setUrl(url);
        //        stagingServer.setDescription("Server URL in Staging environment");


        Info info = new Info()
                .title("Project: EduShare Api Documentation")
                .version("1.0")
                .description("EduShare Api Documentation");

        OpenAPI openApi = new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
        addSecurity(openApi);
        return openApi;
    }

    private void addSecurity(OpenAPI openApi) {
        var components = createComponents();
        var securityItem = new SecurityRequirement().addList(SCHEME_NAME);
        openApi.components(components).addSecurityItem(securityItem);
    }

    private Components createComponents() {
        var components = new Components();
        components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());

        return components;
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }

}

