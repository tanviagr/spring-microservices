import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Configuration
@Configuration
//NEW URL for SWAGGER UI - http://localhost:8080/swagger-ui/ or http://localhost:8080/swagger-ui/index.html
public class SwaggerConfig
{

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
            Arrays.asList("application.json")
    );

    //Bean = docket - documentation type
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getMetaInfo());
//                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
    /*
    http://localhost:8080/v2/api-docs
    some important elements present in the swagger documentation -
    swagger : 2.0 - version of swagger that we're using
    info - high level info about the API
        desc of api
        version
        title
        terms of services
        contact person
        license
    host - where you're hosting the service
    base path - if /api, to call the services, you'd need to add this prefix
    tags - group resources in mutliple categories
    paths - details of all resources we're exposing, and diff operations that can be performed
        /users -> GET, POST (what it consumes, produces, and diff status responses)
        /users/{id} -> GET, DELETE
    definitions
        definitions for resources being returned. These definition are being used in operations being exposed by paths.
        User and his properties
     */
//    Swagger 2
//    All the paths
//    All the APIs

    public ApiInfo getMetaInfo()
    {
        Contact defaultContact = new Contact("Tanvi Agrawal", "http://abc.com", "tanvia29@gmail.com");
        ApiInfo customApiInfo =
                new ApiInfo("Social Media API",
                        "Simple Social Media API",
                        "1.0",
                        "urn:tos",
                        defaultContact,
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
        return customApiInfo;
    }
}
