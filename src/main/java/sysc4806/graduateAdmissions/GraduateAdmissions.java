package sysc4806.graduateAdmissions;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import sysc4806.graduateAdmissions.model.*;
import sysc4806.graduateAdmissions.repositories.UserRepository;

@SpringBootApplication
@Slf4j
public class GraduateAdmissions {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GraduateAdmissions.class)
                .headless(false)
                .run(args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            val users = repository.findAll();
            for (User user : users) log.info(user.toString());
        };
    }
}
