package com.cagnosolutions.nei.shipping.config

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@CompileStatic
@Configuration
class StaticMapping extends WebMvcConfigurerAdapter {

	void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}
