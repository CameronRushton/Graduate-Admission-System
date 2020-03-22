package sysc4806.graduateAdmissions;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GraduateAdmissions {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GraduateAdmissions.class)
                .headless(false)
                .run(args);
    }
}
