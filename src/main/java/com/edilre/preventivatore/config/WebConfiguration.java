package com.edilre.preventivatore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer  {

	private static final String FORWARDINDEX = "forward:/index.html";
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Map "/"
        registry.addViewController("/").setViewName(FORWARDINDEX);
        registry.addViewController("/{x:[\\w\\-]+}").setViewName(FORWARDINDEX);
        
        registry.addViewController("/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}").setViewName(FORWARDINDEX);
    }

}
