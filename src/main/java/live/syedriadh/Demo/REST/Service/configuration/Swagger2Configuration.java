package live.syedriadh.Demo.REST.Service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@Import({SpringDataRestConfiguration.class})
public class Swagger2Configuration {
    @Value("${HTTP.HEADER_NAME}")
    private String header;

    @Value("${HTTP.API_KEY}")
    private String key;

    @Bean
    public Docket swaggerConfiguration() {
        List<SecurityScheme> securitySchemeList = new ArrayList<>();
        securitySchemeList.add(authorization());

        List<SecurityContext> securityContextList = new ArrayList<>();
        securityContextList.add(securityContext());

        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("live.syedriadh.Demo.REST.Service.controller"))
                .paths(PathSelectors.ant("/api/**")).build()
                .apiInfo(apiDetails()).securitySchemes(securitySchemeList)
                .securityContexts(securityContextList);
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "REST Service",
                "REST Service for testing.\n\nAPI access credentials:\nName: key\nValue: "+ key +"\nPass as: header",
                "1.0.0-SNAPSHOT",
                "Open for all",
                new springfox.documentation.service.Contact("Syed Riadh Hossen",
                        null, "hossensyedriadh@gmail.com"),
                "Apache License 2.0",
                "https://github.com/hossensyedriadh/REST-Service-demo/blob/main/LICENSE",
                Collections.emptyList()
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(authenticate()).build();
    }

    private ApiKey authorization() {
        return new ApiKey(header, header, "header");
    }

    private List<SecurityReference> authenticate() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "Authorization Token");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        List<SecurityReference> securityReferenceList = new ArrayList<>();
        securityReferenceList.add(new SecurityReference(header, authorizationScopes));

        return securityReferenceList;
    }
}
