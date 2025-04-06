package nettee.cors.config;

import nettee.cors.properties.CorsProperties;
import nettee.cors.properties.exposed.CorsExposedProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcCorsConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public WebMvcCorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        for (var endpoint : corsProperties.endpoints()) {
            var allowed = endpoint.allowed();
            var exposed = endpoint.exposed();
            var maxAge = endpoint.maxAge();

            registry.addMapping(endpoint.path())
                .allowedHeaders(allowed.headers())
                .allowedMethods(allowed.origins())
                .allowedOrigins(allowed.origins())
                .allowCredentials(allowed.credentials())
                .exposedHeaders(exposed.headers())
                .maxAge(maxAge);
        }
    }
}
