package co.kr.mustit.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * main application class w/ spring-boot, jpa, swagger
 *
 * */
@EnableAutoConfiguration
@ComponentScan(basePackages = "co.kr.mustit")
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    private static final Class<Application> APPLICATION_NAME = Application.class;
    private final Logger logger = LoggerFactory.getLogger(APPLICATION_NAME);

    public static void main(String[] args) {
        SpringApplication.run(APPLICATION_NAME, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APPLICATION_NAME);
    }

    @Bean
    public Docket docApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("apis")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MUSTIT COMMERCE REST API DOCs")
                .description("api docs w/ swagger 2")
                .termsOfServiceUrl("https://mustit.co.kr")
                .contact("")
                .license("")
                .licenseUrl("")
                .version("1.0.0")
                .build();
    }
}
