package br.com.pucrs.resources.src.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    public static final String RESOURCE = "Resources";
    public static final String RESOURCE_TYPE = "Resources Types";
    public static final String USER = "User";

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.pucrs.resources"))
                .build()
                .apiInfo(this.metaData())
                .tags(new Tag(RESOURCE, "Operações referentes a manipulação da entidade Resource."))
                .tags(new Tag(RESOURCE_TYPE, "Operações referentes a manipulação da entidade Resource Type."));
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("RESOURCES API")
                .description("Api responsável pelo microserviço de recursos")
                .license("")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:META-INF/resources/webjars/");
    }
}
