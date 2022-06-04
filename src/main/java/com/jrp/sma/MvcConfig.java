package com.jrp.sma;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.shopme.admin.paging.PagingAndSortingArgumentResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addResourceHandlers(ViewControllerRegistry  registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	

//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//		resolvers.add(new PagingAndSortingArgumentResolver());
//	}

}
