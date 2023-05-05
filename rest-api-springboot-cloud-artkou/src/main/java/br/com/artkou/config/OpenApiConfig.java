package br.com.artkou.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 19 and Springboot 3")
                        .version("V1")
                        .description("description of API")
                        .termsOfService("https://spring-aws.com.br/terms")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://spring-aws.com.br")
                        ));
    }
}
