@EnableSwagger2
@Configuration

public class SwaggerConfig{

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("패키지주소"))
                .paths(PathSelectors.any())
                .build();
    }

}
