package de.rottstegge.restmenu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private static final String RESOURCES_LOCATION = "/public/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**/*")
                .addResourceLocations("classpath:" + RESOURCES_LOCATION)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestResource = location.createRelative(resourcePath);
                        return requestResource.exists() && requestResource.isReadable() ? requestResource : new ClassPathResource(RESOURCES_LOCATION + "index.html");
                    }
                });
    }

    @Configuration
    @Profile({"dev", "development"})
    public static class WebConfigurationDevelopmentProfile {

        public static class CorsAllAllowingFilter implements Filter {

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods",
                        "POST, GET, OPTIONS, DELETE, PUT");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers",
                        "x-requested-with, authorization, content-type");

                if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }

            @Override
            public void init(FilterConfig filterConfig) throws ServletException {

            }
        }

        @Bean
        public Filter corsFilter() {
            return new CorsAllAllowingFilter();
        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {

                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE",
                                    "PATCH");
                }
            };
        }

    }
}
