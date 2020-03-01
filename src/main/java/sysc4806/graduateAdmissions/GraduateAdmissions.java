package sysc4806.graduateAdmissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import sysc4806.graduateAdmissions.model.BuddyInfo;
import sysc4806.graduateAdmissions.model.BuddyInfoRepository;

@SpringBootApplication
public class GraduateAdmissions {

//    @Autowired
//    public void Application(ApplicationContext ctx) {
//        String[] definitions = ctx.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(definitions));
//    }

    private static final Logger log = LoggerFactory.getLogger(GraduateAdmissions.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(GraduateAdmissions.class)
                .headless(false)
                .run(args);
    }

    @Bean
    public CommandLineRunner demo(BuddyInfoRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new BuddyInfo("Jack", "address1", "number", 1));
            repository.save(new BuddyInfo("Chloe", "123 eagfege", "number", 1));
            repository.save(new BuddyInfo("Kim", "245 aefeg", "number", 1));
            repository.save(new BuddyInfo("David", "23 grhgrhr", "number", 1));
            repository.save(new BuddyInfo("Michelle", "980 sdfgeg", "number", 1));

            // fetch all customers
            log.info("BuddyInfos found with findAll():");
            log.info("-------------------------------");
            for (BuddyInfo customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            BuddyInfo customer = repository.findById(1L);
            log.info("BuddyInfo found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("BuddyInfo found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (BuddyInfo bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }
}
