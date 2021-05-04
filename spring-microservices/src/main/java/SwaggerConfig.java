import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Configuration
@Configuration
//Enable Swagger
@EnableSwagger2
//NEW URL for SWAGGER UI - http://localhost:8080/swagger-ui/ or http://localhost:8080/swagger-ui/index.html
public class SwaggerConfig
{
//Bean = docket - documentation type
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2);
    }
//    Swagger 2
//    All the paths
//    All the APIs
}
