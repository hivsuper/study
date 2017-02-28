package com.lxp.notes.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerSpringfoxDocket() {
		StopWatch watch = new StopWatch();
		watch.start();
		Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.genericModelSubstitutes(ResponseEntity.class).select().paths(regex(".*?")).build();
		watch.stop();
		return swaggerSpringMvcPlugin;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("bond-document API Title").description("bond-document API Description")
				.termsOfServiceUrl("bond-document API terms of service").license("bond-document API Licence")
				.licenseUrl("bond-document API License URL").build();
	}
}
