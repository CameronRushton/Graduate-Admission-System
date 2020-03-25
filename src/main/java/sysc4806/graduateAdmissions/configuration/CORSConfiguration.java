package sysc4806.graduateAdmissions.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("localhost:9000", "localhost:8080", "https://graduate-admission-system.herokuapp.com/*",
                        "https://graduate-admission-staging.herokuapp.com/*")
                .maxAge(3600);
    }
}
