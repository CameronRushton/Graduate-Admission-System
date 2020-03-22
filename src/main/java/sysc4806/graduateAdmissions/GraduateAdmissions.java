package sysc4806.graduateAdmissions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
public class GraduateAdmissions {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GraduateAdmissions.class)
                .headless(false)
                .run(args);
    }
}
