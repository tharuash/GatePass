package com.b127.gate.security;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@EnableWebMvc
public class MvcCofigure extends WebMvcConfigurerAdapter{
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		
		super.configurePathMatch(configurer);
		
		configurer.setUseSuffixPatternMatch(false);
	}
}
