package com.perfectoz.libraryapp.Config;

import com.perfectoz.libraryapp.Entity.Book;
import com.perfectoz.libraryapp.Entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    private String AllowedOrigins = "http://localhost:3000";

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] UnsupportedActions = {HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.PUT};

        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);

        disableHttpMethods(Book.class, config, UnsupportedActions);
        disableHttpMethods(Review.class, config, UnsupportedActions);

        cors.addMapping(config.getBasePath()+"/**")
                .allowedOrigins(AllowedOrigins);
    }

    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] UnsupportedActions) {
         config.getExposureConfiguration()
                 .forDomainType(theClass)
                 .withItemExposure((metadata, httpMethods) ->
                         httpMethods.disable(UnsupportedActions))
                 .withCollectionExposure((metadata, httpMethods) ->
                         httpMethods.disable(UnsupportedActions));
    }
}
