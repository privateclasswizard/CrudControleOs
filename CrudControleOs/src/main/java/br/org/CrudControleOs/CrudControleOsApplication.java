package br.org.CrudControleOs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudControleOsApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CrudControleOsApplication.class, args);
    }

	
    public OpenAPI customOpenAPI() {
                    return new OpenAPI()
                            .info(new Info()
                            .title("Servidor de tarefas")
                            .version("1.0.0")
                            .description("Exemplo de REST API para tarefas")
          );


    }
}
